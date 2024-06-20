package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import jakarta.validation.Valid;

public interface PagamentoService {

    PagamentoResponseDTO create(@Valid PagamentoDTO dto);
    PagamentoResponseDTO update(Long id, PagamentoDTO dto);
    PagamentoResponseDTO efetuarPagamento(Long id, PagamentoDTO dto);
    PagamentoResponseDTO findById(Long id);
    void delete(Long id);
    List<PagamentoResponseDTO> findAll();
    List<PagamentoResponseDTO> findByCliente(Long idCliente);
    
}