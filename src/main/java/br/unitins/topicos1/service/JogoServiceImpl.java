package br.unitins.topicos1.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.JogoDTO;
import br.unitins.topicos1.dto.JogoResponseDTO;
import br.unitins.topicos1.model.Empresa;
import br.unitins.topicos1.model.FaixaEtaria;
import br.unitins.topicos1.model.Jogo;
import br.unitins.topicos1.model.Plataforma;
import br.unitins.topicos1.model.Tema;
import br.unitins.topicos1.repository.EmpresaRepository;
import br.unitins.topicos1.repository.JogoRepository;
import br.unitins.topicos1.repository.PlataformaRepository;
import br.unitins.topicos1.repository.TemaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class JogoServiceImpl implements JogoService {

    @Inject
    JogoRepository jogoRepository;

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    TemaRepository temaRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    Validator validator;

    @Override
    public List<JogoResponseDTO> getAll() {

        List<Jogo> list = jogoRepository.findAll().list();
        return list.stream().map(e -> JogoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public JogoResponseDTO create(@Valid JogoDTO jogoDTO) {
        validar(jogoDTO);
        
        Jogo jogo = new Jogo();
        jogo.setNome(jogoDTO.nome());
        jogo.setPreco(jogoDTO.preco());
        jogo.setDesconto(jogoDTO.desconto());
        jogo.setFaixaEtaria(FaixaEtaria.valueOf(jogoDTO.idfaixaEtaria()));
        jogo.setEmpresa(acharEmpresa(jogoDTO.idempresa()));
        jogo.setListaTema(acharTemas(jogoDTO.idsTemas()));
        jogo.setListaPlataforma(acharPlataformas(jogoDTO.idsPlataformas()));

        jogoRepository.persist(jogo);
        return JogoResponseDTO.valueOf(jogo);

    }

    @Override
    @Transactional
    public JogoResponseDTO update(Long id, JogoDTO jogoDTO){
        validar(jogoDTO);
        
        Jogo jogoBanco = jogoRepository.findById(id);

        jogoBanco.setNome(jogoDTO.nome());
        jogoBanco.setPreco(jogoDTO.preco());
        jogoBanco.setDesconto(jogoDTO.desconto());
        jogoBanco.setFaixaEtaria(FaixaEtaria.valueOf(jogoDTO.idfaixaEtaria()));
        jogoBanco.setEmpresa(acharEmpresa(jogoDTO.idempresa()));
        jogoBanco.getListaTema().clear();
        jogoBanco.getListaPlataforma().clear();
        jogoBanco.setListaTema(acharTemas(jogoDTO.idsTemas()));
        jogoBanco.setListaPlataforma(acharPlataformas(jogoDTO.idsPlataformas()));


        return JogoResponseDTO.valueOf(jogoBanco);
    }
    public void validarNomeJogo(String nome) {
        Jogo jogo = jogoRepository.findByNomeCompleto(nome);
        if (jogo != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    private Empresa acharEmpresa(long idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa);
        if (empresa == null) {
            throw new NotFoundException("Empresa não encontrada.");
        }
        return empresa;
    }

    private Tema acharTema(long idTema) {
        Tema tema = temaRepository.findById(idTema);
        if (tema == null) {
            throw new NotFoundException("Tema não encontrada.");
        }
        return tema;
    }

    private Plataforma acharPlataforma(long idPlataforma) {
        Plataforma plataforma = plataformaRepository.findById(idPlataforma);
        if (plataforma == null) {
            throw new NotFoundException("Plataforma não encontrada.");
        }
        return plataforma;
    }

    private void validar(JogoDTO JogoDTO) {
        Set<ConstraintViolation<JogoDTO>> violations = validator.validate(JogoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
    private List<Tema> acharTemas(List<Long> idsTemas) {
        return idsTemas.stream()
            .map(this::acharTema)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private List<Plataforma> acharPlataformas(List<Long> idsPlataformas) {
        return idsPlataformas.stream()
            .map(this::acharPlataforma)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jogoRepository.deleteById(id);
    }

    @Override
    public List<JogoResponseDTO> findByNome(String nome) {
        List<Jogo> list = jogoRepository.findByNome(nome).list();
        return list.stream().map(e -> JogoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return jogoRepository.count();
    }

    @Override
    public JogoResponseDTO findById(Long id) {
        return JogoResponseDTO.valueOf(jogoRepository.findById(id));
    }
    
    @Override
    public List<JogoResponseDTO> findAll(){
        return jogoRepository.listAll().stream().map(Jogo -> JogoResponseDTO.valueOf(Jogo)).toList();
    }
    @Override
    public long countByNome(String nome) {
        return jogoRepository.findByNome(nome).count();
    }
}
