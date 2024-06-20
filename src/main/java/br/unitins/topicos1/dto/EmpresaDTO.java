package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(
    @NotBlank(message = "O nome deve ser informado.")
    @Size(max = 30, message = "O campo nome deve possuir no máximo 30 caracteres.")
    String nome,

    @NotBlank(message = "O cnpj deve ser informado.")
    @Size(max = 14, message = "O campo cnpj deve possuir 14 caracteres.")
    String cnpj,

    @NotBlank(message = "O email deve ser informado.")
    @Size(max = 50, message = "O campo email deve possuir no máximo 50 caracteres.")
    String email
    ) {
}
