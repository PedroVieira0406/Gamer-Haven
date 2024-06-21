package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.FuncionarioDTO;
import br.unitins.topicos1.dto.FuncionarioResponseDTO;
import br.unitins.topicos1.dto.LoginDTO;
import br.unitins.topicos1.dto.LoginResponseDTO;
import br.unitins.topicos1.model.Funcionario;
import br.unitins.topicos1.model.Login;
import br.unitins.topicos1.repository.FuncionarioRepository;
import br.unitins.topicos1.repository.LoginRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository funcionarioRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Inject
    LoginRepository loginRepository;

    @Override
    public List<FuncionarioResponseDTO> getAll() {

        List<Funcionario> list = funcionarioRepository.findAll().list();
        return list.stream().map(e -> FuncionarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        return FuncionarioResponseDTO.valueOf(funcionarioRepository.findById(id));
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO create(@Valid FuncionarioDTO funcionarioDTO,@Valid LoginDTO loginDTO) {

        Login login = new Login();
        login.setName(loginDTO.name());
        // gereando o hash da senha
        login.setSenha(hashService.getHashSenha(loginDTO.senha()));

        // salvando o usuario
        loginRepository.persist(login);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setEmail(funcionarioDTO.email());
        funcionario.setCpf(funcionarioDTO.cpf());
        funcionario.setCargo(funcionarioDTO.cargo());
        funcionario.setLogin(login);
        
        funcionarioRepository.persist(funcionario);

        return FuncionarioResponseDTO.valueOf(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO update(Long id, FuncionarioDTO funcionarioDTO) {
        validar(funcionarioDTO);

        Funcionario funcionarioBanco = funcionarioRepository.findById(id);
        if (funcionarioBanco == null) {
            throw new EntityNotFoundException("Funcionario cão achado com o id " + id);
        }
        
        funcionarioBanco.setNome(funcionarioDTO.nome());
        funcionarioBanco.setEmail(funcionarioDTO.email());
        funcionarioBanco.setCpf(funcionarioDTO.cpf());
        funcionarioBanco.setCargo(funcionarioDTO.cargo());

        return FuncionarioResponseDTO.valueOf(funcionarioBanco);
    }

    private void validar(FuncionarioDTO funcionarioDTO) {
        Set<ConstraintViolation<FuncionarioDTO>> violations = validator.validate(funcionarioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
    public void validarNomeFuncionario(String nome) {
        Funcionario funcionario = funcionarioRepository.findByNomeCompleto(nome);
        if (funcionario != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        List<Funcionario> list = funcionarioRepository.findByNome(nome).list();
        return list.stream().map(e -> FuncionarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<FuncionarioResponseDTO> findByCargo(String cargo) {
        List<Funcionario> list = funcionarioRepository.findByCargo(cargo).list();
        return list.stream().map(e -> FuncionarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<FuncionarioResponseDTO> findAll(){
        return funcionarioRepository.listAll().stream().map(Funcionario -> FuncionarioResponseDTO.valueOf(Funcionario)).toList();
    }

    @Override
    public long count() {
        return funcionarioRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return funcionarioRepository.findByNome(nome).count();
    }

    public LoginResponseDTO login(String name, String senha) {
        Funcionario funcionario = funcionarioRepository.findByUsernameAndSenha(name, senha);
        return LoginResponseDTO.valueOf(funcionario.getLogin());
    }
}
