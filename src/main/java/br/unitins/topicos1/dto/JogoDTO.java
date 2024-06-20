package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record JogoDTO(
    @NotBlank(message = "O nome deve ser informado.")
    @Size(max = 30, message = "O campo nome deve possuir no máximo 30 caracteres.")
    String nome,

    @DecimalMax(value = "9999.99", message = "O preço deve ser um valor válido.")
    Float preco,

    @DecimalMax(value = "9999.99", message = "O desconto deve ser um valor válido.")
    Float desconto,
    
    @NotNull(message = "O id da faixa deve ser informado.")
    Integer idfaixaEtaria,

    @NotNull(message = "O id da empresa deve ser informado.")
    Long idempresa,

    @NotNull(message = "Os ids das plataformas devem ser informados.")
    List<Long> idsPlataformas,

    @NotNull(message = "Os ids dos temas devem ser informados.")
    List<Long> idsTemas
) {}