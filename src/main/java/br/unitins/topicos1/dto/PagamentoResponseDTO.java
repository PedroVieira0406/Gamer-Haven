package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cartao;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.ModoPagamento;
import br.unitins.topicos1.model.Pagamento;

public record PagamentoResponseDTO (
    Long id,
    Float valorCompra,
    Float valorPago,
    Cliente pagante,
    Boolean statusPagamento,
    ModoPagamento modoPagamento,
    Cartao cartao
) {
    public static PagamentoResponseDTO valueOf(Pagamento e) {
        return new PagamentoResponseDTO(
            e.getId(),
            e.getValorCompra(),
            e.getValorPago(),
            e.getCliente(),
            e.isStatus(),
            e.getModoPagamento(),
            e.getCartao()
        );
    }
}
