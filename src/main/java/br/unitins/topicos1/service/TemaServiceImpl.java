package br.unitins.topicos1.service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.TemaDTO;
import br.unitins.topicos1.dto.TemaResponseDTO;
import br.unitins.topicos1.model.Tema;
import br.unitins.topicos1.repository.TemaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@ApplicationScoped
public class TemaServiceImpl implements TemaService {

    @Inject
    TemaRepository temaRepository;

    @Inject
    Validator validator;

    @Override
    public List<TemaResponseDTO> getAll() {

        List<Tema> list = temaRepository.findAll().list();
        return list.stream().map(e -> TemaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public TemaResponseDTO findById(Long id) {
        return TemaResponseDTO.valueOf(temaRepository.findById(id));
    }

    @Override
    @Transactional
    public TemaResponseDTO create(@Valid TemaDTO temaDTO) {

        Tema tema = new Tema();
        tema.setNome(temaDTO.nome());

        temaRepository.persist(tema);

        return TemaResponseDTO.valueOf(tema);
    }

    @Override
    @Transactional
    public TemaResponseDTO update(Long id, TemaDTO temaDTO){
    validar(temaDTO);

    Tema temaBanco = temaRepository.findById(id);

    temaBanco.setNome(temaDTO.nome());

    return TemaResponseDTO.valueOf(temaBanco);
}

    private void validar(TemaDTO temaDTO) {
        Set<ConstraintViolation<TemaDTO>> violations = validator.validate(temaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    public void validarNomeTema(String nome) {
        Tema tema = temaRepository.findByNomeCompleto(nome);
        if (tema != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        temaRepository.deleteById(id);
    }

    @Override
    public List<TemaResponseDTO> findByNome(String nome) {
        List<Tema> list = temaRepository.findByNome(nome).list();
        return list.stream().map(e -> TemaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return temaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return temaRepository.findByNome(nome).count();
    }
}
