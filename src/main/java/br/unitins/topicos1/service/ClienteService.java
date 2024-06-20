package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.LoginResponseDTO;
import jakarta.validation.Valid;

public interface ClienteService {

    List<ClienteResponseDTO> getAll();
    ClienteResponseDTO findById(Long id);
    ClienteResponseDTO create (@Valid ClienteDTO dto);
    ClienteResponseDTO update(Long id, ClienteDTO dto);
    void delete(Long id);
    
    List<ClienteResponseDTO> findByNome(String nome);
    List<ClienteResponseDTO> findByEmail(String email);
    long count();

    long countByNome(String nome);
    LoginResponseDTO login(String name, String senha);
    
}