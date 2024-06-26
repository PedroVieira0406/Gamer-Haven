package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(

    @NotBlank(message = "O UserName deve ser informado.")
    @Size(max = 30, message = "O campo UserName deve possuir no máximo 30 caracteres.")
    String name,
    
    @NotBlank(message = "A senha deve ser informado.")
    @Size(max = 255, message = "O campo senha deve possuir no máximo 30 caracteres.")
    String senha

){}
