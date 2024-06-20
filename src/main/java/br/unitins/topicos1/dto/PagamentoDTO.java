package br.unitins.topicos1.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PagamentoDTO(

@DecimalMax(value = "9999.99", message = "O preço deve ser um valor válido.")
    @Positive(message = "O valor da compra deve ser positivo.")
    Float valorCompra,

    @DecimalMax(value = "9999.99", message = "O valor pago deve ser um valor válido.")
    @Positive(message = "O valor pago deve ser positivo.")
    Float valorPago,

    @NotNull(message = "O id do cliente deve ser informado.")
    Long idCliente,

    @NotNull(message = "O modo de pagamento deve ser informado.")
    Integer modoPagamento,

    String nCartao,

    @NotNull(message = "O status deve ser informado.")
    Boolean status
) {}