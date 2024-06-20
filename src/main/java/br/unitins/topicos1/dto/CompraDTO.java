package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

public record CompraDTO(

    @NotNull(message = "O id do pagante deve ser informado.")
    Long idPagante,

    @NotNull(message = "O id do jogo deve ser informado.")
    Long idJogo,

    @NotNull(message = "O id do dono deve ser informado.")
    Long idDono
    ) {}

