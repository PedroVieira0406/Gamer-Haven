package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CartaoDTO;
import br.unitins.topicos1.dto.CartaoResponseDTO;
import jakarta.validation.Valid;

public interface CartaoService {
    
    CartaoResponseDTO findById(Long id);
    CartaoResponseDTO create (@Valid CartaoDTO dto);
    CartaoResponseDTO update(Long idCartao, CartaoDTO dto);
    void delete(Long id);
    List<CartaoResponseDTO> findByDono(Long dono);

    List<CartaoResponseDTO> getAll();
}