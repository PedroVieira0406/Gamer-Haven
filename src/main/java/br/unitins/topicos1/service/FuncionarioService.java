package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.FuncionarioDTO;
import br.unitins.topicos1.dto.FuncionarioResponseDTO;
import br.unitins.topicos1.dto.LoginResponseDTO;
import jakarta.validation.Valid;

public interface FuncionarioService {

    List<FuncionarioResponseDTO> getAll();
    FuncionarioResponseDTO findById(Long id);
    FuncionarioResponseDTO create (@Valid FuncionarioDTO funcionarioDTO);
    FuncionarioResponseDTO update(Long id, FuncionarioDTO dto);
    void delete(Long id);
    List<FuncionarioResponseDTO> findAll();
    List<FuncionarioResponseDTO> findByNome(String nome);
    List<FuncionarioResponseDTO> findByCargo(String cargo);
    List<FuncionarioResponseDTO> findByEmail(String email);
    long count();

    long countByNome(String nome);
    LoginResponseDTO login(String username, String hash);
    
}