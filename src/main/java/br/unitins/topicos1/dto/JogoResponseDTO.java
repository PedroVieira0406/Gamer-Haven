package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.FaixaEtaria;
import br.unitins.topicos1.model.Jogo;

public record JogoResponseDTO(
    Long id,
    String nome, 
    Float preco, 
    Float desconto,
    String nomeImagem,
    FaixaEtaria faixaEtaria , 
    EmpresaResponseDTO empresa, 
    List<PlataformaResponseDTO> plataforma, 
    List<TemaResponseDTO> tema) 
    {
    public static JogoResponseDTO valueOf(Jogo jogo) {

        
            List<PlataformaResponseDTO> listaPlataforma = jogo.getListaPlataforma()
                                                .stream()
                                                .map(PlataformaResponseDTO:: valueOf)
                                                .toList();

            List<TemaResponseDTO> listaTema = jogo.getListaTema()
                                                .stream()
                                                .map(TemaResponseDTO:: valueOf)
                                                .toList();

        return new JogoResponseDTO(

            jogo.getId(),
            jogo.getNome(),
            jogo.getPreco(),
            jogo.getDesconto(),
            jogo.getNomeImagem(),
            jogo.getFaixaEtaria(),
            EmpresaResponseDTO.valueOf(jogo.getEmpresa()),
            listaPlataforma,
            listaTema
        );
    }
}