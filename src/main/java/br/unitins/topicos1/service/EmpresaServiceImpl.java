package br.unitins.topicos1.service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.EmpresaDTO;
import br.unitins.topicos1.dto.EmpresaResponseDTO;
import br.unitins.topicos1.model.Empresa;
import br.unitins.topicos1.repository.EmpresaRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@ApplicationScoped
public class EmpresaServiceImpl implements EmpresaService {

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    Validator validator;

    @Override
    public List<EmpresaResponseDTO> getAll() {

        List<Empresa> list = empresaRepository.findAll().list();
        return list.stream().map(e -> EmpresaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public EmpresaResponseDTO create(@Valid EmpresaDTO empresaDTO) {
        validarNomeEmpresa(empresaDTO.nome());

        Empresa entity = new Empresa();
        entity.setNome(empresaDTO.nome());
        entity.setCnpj(empresaDTO.cnpj());
        entity.setEmail(empresaDTO.email());

        empresaRepository.persist(entity);

        return EmpresaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public EmpresaResponseDTO update(Long id, EmpresaDTO empresaDTO){
        validar(empresaDTO);
   
        Empresa entity = empresaRepository.findById(id);

        entity.setNome(empresaDTO.nome());
        entity.setCnpj(empresaDTO.cnpj());
        entity.setEmail(empresaDTO.email());


        return EmpresaResponseDTO.valueOf(entity);
    }

    private void validar(EmpresaDTO empresaDTO) {
        Set<ConstraintViolation<EmpresaDTO>> violations = validator.validate(empresaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
    public void validarNomeEmpresa(String nome) {
        Empresa empresa = empresaRepository.findByNomeCompleto(nome);
        if (empresa != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public EmpresaResponseDTO findById(Long id){
        return EmpresaResponseDTO.valueOf(empresaRepository.findById(id));
    }

    @Override
    public List<EmpresaResponseDTO> findAll(){
        return empresaRepository.listAll().stream().map(Empresa -> EmpresaResponseDTO.valueOf(Empresa)).toList();
    }

    @Override
    public List<EmpresaResponseDTO> findByNome(String nome) {
        List<Empresa> list = empresaRepository.findByNome(nome).list();
        return list.stream().map(e -> EmpresaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return empresaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return empresaRepository.findByNome(nome).count();
    }
}
