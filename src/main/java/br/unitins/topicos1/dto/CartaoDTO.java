package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

public record CartaoDTO (
    
    @NotNull(message = "O id da faixa deve ser informado.")
    Integer idCartaoMarca,

    @NotNull(message = "O numero do cartao deve ser informado.")
    String nCartao,

    @NotNull(message = "O mês da validade deve ser informado.")
    Integer mesValid,

    @NotNull(message = "O ano da validade deve ser informado.")
    Integer anoValid,

    @NotNull(message = "O numero do cartao deve ser informado.")
    String numSeguranca,

    @NotNull(message = "O nome deve ser informado.")
    String nome,

    @NotNull(message = "O sobrenome deve ser informado.")
    String sobrenome,

    @NotNull(message = "O cep deve ser informado.")
    String cep,

    @NotNull(message = "A cidade deve ser informado.")
    String cidade,

    @NotNull(message = "O pais deve ser informado.")
    String pais,

    @NotNull(message = "O dono deve ser informado.")
    Long dono
){}
