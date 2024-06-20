package br.unitins.topicos1.dto;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.model.Cartao;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Jogo;

public record ClienteResponseDTO(
    Long id,
    String name,
    String nome,
    String email,
    String cpf,
    Integer idade,
    String nomeImagem,
    List<JogoResponseDTO> biblioteca,
    List<CartaoResponseDTO> cartoes
    
) {
    public static ClienteResponseDTO valueOf(Cliente cliente) {

        List<JogoResponseDTO> biblioteca = new ArrayList<>();
        List<CartaoResponseDTO> cartoes = new ArrayList<>();


        if (cliente.getBiblioteca() != null) {
            for (Jogo jogo : cliente.getBiblioteca()) {
                biblioteca.add(JogoResponseDTO.valueOf(jogo));
            }
        }
        
        if (cliente.getCartoes() != null) {
            for (Cartao cartao : cliente.getCartoes()) {
                cartoes.add(CartaoResponseDTO.valueOf(cartao));
            }
        }

        return new ClienteResponseDTO(
            cliente.getId(),
            cliente.getLogin().getName(),
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getCpf(),
            cliente.getIdade(),
            cliente.getNomeImagem(),
            biblioteca,
            cartoes);
    }
    
}
