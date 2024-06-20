package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EmpresaDTO;
import br.unitins.topicos1.dto.EmpresaResponseDTO;
import jakarta.validation.Valid;

public interface EmpresaService {
    
    EmpresaResponseDTO findById(Long id);
    EmpresaResponseDTO create (@Valid EmpresaDTO dto);
    EmpresaResponseDTO update(Long id, EmpresaDTO dto);
    void delete(Long id);
    List<EmpresaResponseDTO> getAll();
    List<EmpresaResponseDTO> findAll();
    List<EmpresaResponseDTO> findByNome(String nome);

    long count();

    long countByNome(String nome);
    
}