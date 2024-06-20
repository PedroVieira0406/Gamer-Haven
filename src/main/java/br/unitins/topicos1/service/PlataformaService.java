package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PlataformaDTO;
import br.unitins.topicos1.dto.PlataformaResponseDTO;
import jakarta.validation.Valid;

public interface PlataformaService {

    List<PlataformaResponseDTO> getAll();
    PlataformaResponseDTO findById(Long id);
    PlataformaResponseDTO create (@Valid PlataformaDTO dto);
    PlataformaResponseDTO update(Long id, PlataformaDTO dto);
    void delete(Long id);
    
    List<PlataformaResponseDTO> findByNome(String nome);

    long count();

    long countByNome(String nome);
    
}