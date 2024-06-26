package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteDTO(

@NotBlank(message = "O UserName deve ser informado.")
@Size(max = 30, message = "O campo UserName deve possuir no máximo 30 caracteres.")
String name,

@NotBlank(message = "A senha deve ser informado.")
@Size(max = 255, message = "O campo senha deve possuir no máximo 30 caracteres.")
String senha,

@NotBlank(message = "O nome deve ser informado.")
@Size(max = 30, message = "O campo nome deve possuir no máximo 30 caracteres.")
String nome,

@NotBlank(message = "O email deve ser informado.")
@Email(message= "E-mail inválido.")
@Size(max = 50, message = "O campo email deve possuir no máximo 50 caracteres.")
String email,

@NotBlank(message = "O cpf deve ser informado.")
@Size(max = 15, message = "O campo cpf deve possuir 15 caracteres.")
String cpf,

@NotNull(message = "A idade deve ser informada.")
Integer idade
){}
