package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TemaDTO;
import br.unitins.topicos1.dto.TemaResponseDTO;
import jakarta.validation.Valid;

public interface TemaService {

    List<TemaResponseDTO> getAll();
    TemaResponseDTO findById(Long id);
    TemaResponseDTO create (@Valid TemaDTO dto);
    TemaResponseDTO update(Long id, TemaDTO dto);
    void delete(Long id);
    
    List<TemaResponseDTO> findByNome(String nome);
    long count();

    long countByNome(String nome);
    
}