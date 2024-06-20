package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Tema;

public record TemaResponseDTO(Long id, String nome){
    public static TemaResponseDTO valueOf(Tema tema){
        return new TemaResponseDTO(
        tema.getId(),
        tema.getNome());
    }
    
}
