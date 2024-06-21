package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.CadastroClienteDTO;
import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.LoginDTO;
import br.unitins.topicos1.dto.LoginResponseDTO;
import br.unitins.topicos1.dto.UpdatePasswordDTO;
import br.unitins.topicos1.dto.UpdateUsernameDTO;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Login;
import br.unitins.topicos1.repository.ClienteRepository;
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
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    Validator validator;

    @Inject
    HashService hashService;

    @Inject
    LoginRepository loginRespository;

    @Inject
    LoginRepository loginRepository;

    @Override
    public List<ClienteResponseDTO> getAll() {

        List<Cliente> list = clienteRepository.findAll().list();
        return list.stream().map(e -> ClienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        return ClienteResponseDTO.valueOf(clienteRepository.findById(id));
    }

    @Override
    @Transactional
    public ClienteResponseDTO cadastro(@Valid CadastroClienteDTO clienteDTO) {
        Login login = new Login();
        login.setName(clienteDTO.name());
        // gereando o hash da senha
        login.setSenha(hashService.getHashSenha(clienteDTO.senha()));

        // salvando o usuario
        loginRepository.persist(login);


        Cliente entity = new Cliente();
        entity.setNome(clienteDTO.nome());
        entity.setCpf(clienteDTO.cpf());
        entity.setEmail(clienteDTO.email());
        entity.setIdade(clienteDTO.idade());
        entity.setLogin(login);

        clienteRepository.persist(entity);

        return ClienteResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO clienteDTO, @Valid LoginDTO loginDTO) {
        Login login = new Login();
        login.setName(loginDTO.name());
        // gereando o hash da senha
        login.setSenha(hashService.getHashSenha(loginDTO.senha()));

        // salvando o usuario
        loginRepository.persist(login);


        Cliente entity = new Cliente();
        entity.setNome(clienteDTO.nome());
        entity.setCpf(clienteDTO.cpf());
        entity.setEmail(clienteDTO.email());
        entity.setIdade(clienteDTO.idade());
        entity.setLogin(login);

        clienteRepository.persist(entity);

        return ClienteResponseDTO.valueOf(entity);
    }

@Override
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteDTO ClienteDTO) {
        validar(ClienteDTO);

        Cliente ClienteBanco = clienteRepository.findById(id);
        if (ClienteBanco == null) {
            throw new EntityNotFoundException("Cliente não achado com o id " + id);
        }
        
        ClienteBanco.setNome(ClienteDTO.nome());
        ClienteBanco.setEmail(ClienteDTO.email());
        ClienteBanco.setCpf(ClienteDTO.cpf());
        ClienteBanco.setIdade(ClienteDTO.idade());

        return ClienteResponseDTO.valueOf(ClienteBanco);
    }

        @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordDTO dto) {

        Cliente cliente = clienteRepository.findById(id);
        String hashSenhaAntiga = hashService.getHashSenha(dto.oldPassword());
        if(dto.newPassword() != dto.newPassword2())throw new ValidationException("ERRO", "Senhas nao comprativeis");
        if (cliente != null) {
            if (cliente.getLogin().getSenha().equals(hashSenhaAntiga)) {
                String hashNovaSenha = hashService.getHashSenha(dto.newPassword());
                cliente.getLogin().setSenha(hashNovaSenha);
            } else {
                throw new ValidationException("ERRO", "Senha antiga nao corresponde");
            }
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    @Transactional
    public void updateUsername(Long id, UpdateUsernameDTO dto) {

        Cliente cliente = clienteRepository.findById(id);

        if (cliente != null) {
            cliente.getLogin().setName(dto.newUsername());;
        } else {
            throw new NotFoundException();
        }
    }

    public void validarNomeCliente(String nome) {
        Cliente cliente = clienteRepository.findByNomeCompleto(nome);
        if (cliente != null)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe.");
    }

    private void validar(ClienteDTO clienteDTO) {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        List<Cliente> list = clienteRepository.findByNome(nome).list();
        return list.stream().map(e -> ClienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<ClienteResponseDTO> findByEmail(String email) {
        List<Cliente> list = clienteRepository.findByEmail(email).list();
        return list.stream().map(e -> ClienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return clienteRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return clienteRepository.findByNome(nome).count();
    }

    public LoginResponseDTO login(String name, String senha) {
        Cliente cliente = clienteRepository.findByUsernameAndSenha(name, senha);
        return LoginResponseDTO.valueOf(cliente.getLogin());
    }
}
