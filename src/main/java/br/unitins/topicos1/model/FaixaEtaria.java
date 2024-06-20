package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FaixaEtaria {
    LIVRE(1, "Livre"),
    DEZ_ANOS(2, "+10"),
    DOZE_ANOS(3, "+12"),
    QUATORZE_ANOS(4, "+14"),
    DEZESSEIS_ANOS(5, "+16"),
    DEZOITO_ANOS(6, "+18");

    private int id;
    private String nome;

    FaixaEtaria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static FaixaEtaria valueOf(Integer id) throws IllegalArgumentException {
        for (FaixaEtaria faixaEtaria : FaixaEtaria.values()) {
            if (faixaEtaria.id == id)
                return faixaEtaria;
        }
        throw new IllegalArgumentException("id de faixa etaria inválido.");
    }

}