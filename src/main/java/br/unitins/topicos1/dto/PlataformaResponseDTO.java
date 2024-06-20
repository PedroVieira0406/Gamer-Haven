package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Plataforma;

public record PlataformaResponseDTO(Long id, String nome){
    public static PlataformaResponseDTO valueOf(Plataforma plataforma){
        return new PlataformaResponseDTO(
        plataforma.getId(),
        plataforma.getNome());
    }
    
}
