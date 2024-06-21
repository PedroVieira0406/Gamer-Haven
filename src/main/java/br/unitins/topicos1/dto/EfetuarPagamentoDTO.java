package br.unitins.topicos1.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EfetuarPagamentoDTO(

    @DecimalMax(value = "9999.99", message = "O valor pago deve ser um valor válido.")
    @Positive(message = "O valor pago deve ser positivo.")
    Float valorPago,

    @NotNull(message = "O modo de pagamento deve ser informado.")
    Integer modoPagamento,

    String nCartao
) {}