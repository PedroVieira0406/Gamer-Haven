package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.JogoDTO;
import br.unitins.topicos1.dto.JogoResponseDTO;
import jakarta.validation.Valid;

public interface JogoService {

    List<JogoResponseDTO> getAll();
    JogoResponseDTO create (@Valid JogoDTO dto);
    JogoResponseDTO update(Long id, JogoDTO dto);
    void delete(Long id);
    List<JogoResponseDTO> findAll();
    List<JogoResponseDTO> findByNome(String nome);
    JogoResponseDTO findById(Long id);
    long count();

    long countByNome(String nome);
    
}
