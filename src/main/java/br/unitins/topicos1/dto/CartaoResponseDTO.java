package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cartao;
import br.unitins.topicos1.model.CartaoMarca;

public record CartaoResponseDTO(
Long id,
CartaoMarca cartaoMarca,
String nCartao,
Integer anoValid,
String nome,
String usuario
){

    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(

            cartao.getId(),
            cartao.getCartaoMarca(),
            cartao.getnCartao(),
            cartao.getAnoValid(),
            cartao.getNome(),
            cartao.getCliente().getNome()
        );
    }
}
