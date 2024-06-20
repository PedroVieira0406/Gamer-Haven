package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Compra;

public record CompraResponseDTO (

Long id,
Float preco,
String pagante,
String jogo,
String dono,
Long idPagamento,
Boolean statusPagamento
){
    public static CompraResponseDTO valueOf(Compra compra) {
    return new CompraResponseDTO(
        compra.getId(),
        compra.getPreco(),
        compra.getPagante().getLogin().getName(),
        compra.getJogo().getNome(),
        compra.getDono().getLogin().getName(),
        compra.getPagamento().getId(),
        compra.getPagamento().isStatus());
    }
}
