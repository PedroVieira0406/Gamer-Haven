package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlataformaDTO(
    @NotBlank(message = "O nome deve ser informado.")
    @Size(max = 30, message = "O campo nome deve possuir no máximo 30 caracteres.")
    String nome
){}