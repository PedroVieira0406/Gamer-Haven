package br.unitins.topicos1.service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.PlataformaDTO;
import br.unitins.topicos1.dto.PlataformaResponseDTO;
import br.unitins.topicos1.model.Plataforma;
import br.unitins.topicos1.repository.PlataformaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@ApplicationScoped
public class PlataformaServiceImpl implements PlataformaService {

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    Validator validator;

    @Override
    public List<PlataformaResponseDTO> getAll() {

        List<Plataforma> list = plataformaRepository.findAll().list();
        return list.stream().map(e -> PlataformaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public PlataformaResponseDTO findById(Long id) {
        return PlataformaResponseDTO.valueOf(plataformaRepository.findById(id));
    }

    @Override
    @Transactional
    public PlataformaResponseDTO create(@Valid PlataformaDTO plataformaDTO) {

        Plataforma plataforma = new Plataforma();
        plataforma.setPlataforma(plataformaDTO.nome());

        plataformaRepository.persist(plataforma);

        return PlataformaResponseDTO.valueOf(plataforma);
    }

    @Override
    @Transactional
    public PlataformaResponseDTO update(Long id, PlataformaDTO plataformaDTO){
        validar(plataformaDTO);
   
        Plataforma plataformaBanco = plataformaRepository.findById(id);

        plataformaBanco.setPlataforma(plataformaDTO.nome());


        return PlataformaResponseDTO.valueOf(plataformaBanco);
    }

    public void validarNomePlataforma(String nome) {
        Plataforma plataforma = plataformaRepository.findByNomeCompleto(nome);
        if (plataforma != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    private void validar(PlataformaDTO plataformaDTO) {
        Set<ConstraintViolation<PlataformaDTO>> violations = validator.validate(plataformaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        plataformaRepository.deleteById(id);
    }

    @Override
    public List<PlataformaResponseDTO> findByNome(String nome) {
        List<Plataforma> list = plataformaRepository.findByNome(nome).list();
        return list.stream().map(e -> PlataformaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return plataformaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return plataformaRepository.findByNome(nome).count();
    }
}
