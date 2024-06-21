package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CompraDTO;
import br.unitins.topicos1.dto.CompraResponseDTO;
import jakarta.validation.Valid;

public interface CompraService {

    CompraResponseDTO create(@Valid CompraDTO dto);
    CompraResponseDTO findById(Long id);
    CompraResponseDTO update(@Valid CompraDTO dto, Long id);
    void delete(Long id);
    void ativandoJogo(Long idPagamento);
    List<CompraResponseDTO> getAll();
    List<CompraResponseDTO> findAll();
    List<CompraResponseDTO> findByDono(Long idDono);
    List<CompraResponseDTO> findByPagante(Long idCliente);
    List<CompraResponseDTO> findByJogo(Long idJogo);
    
}