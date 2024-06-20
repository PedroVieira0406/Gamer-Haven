package br.unitins.topicos1.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;

public record CompraDTO(

    @DecimalMax(value = "9999.99", message = "O preço deve ser um valor válido.")
    Float preco,

    @NotNull(message = "O id do pagante deve ser informado.")
    Long idPagante,

    @NotNull(message = "O id do jogo deve ser informado.")
    Long idJogo,

    @NotNull(message = "O id do dono deve ser informado.")
    Long idDono
    ) {}

