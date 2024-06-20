package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cartao;
import br.unitins.topicos1.model.ModoPagamento;
import br.unitins.topicos1.model.Pagamento;

public record PagamentoResponseDTO (
    Long id,
    Float valorCompra,
    Float valorPago,
    Long clienteId,
    Boolean statusPagamento,
    ModoPagamento modoPagamento,
    CartaoResponseDTO cartao
) {
    public static PagamentoResponseDTO valueOf(Pagamento e) {
        Cartao cartao = e.getCartao();
        return new PagamentoResponseDTO(
            e.getId(),
            e.getValorCompra(),
            e.getValorPago(),
            e.getClienteId(),
            e.isStatus(),
            e.getModoPagamento(),
            cartao != null ? CartaoResponseDTO.valueOf(cartao) : null
        );
    }
}