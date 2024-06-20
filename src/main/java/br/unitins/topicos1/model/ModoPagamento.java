package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ModoPagamento {
    PIX(1, "pix"),
    CREDITO(2, "crédito"),
    DEBITO(3, "debito");

    private int id;
    private String nome;

    ModoPagamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static ModoPagamento valueOf(Integer id) throws IllegalArgumentException {
        for (ModoPagamento ModoPagamento : ModoPagamento.values()) {
            if (ModoPagamento.id == id)
                return ModoPagamento;
        }
        throw new IllegalArgumentException("id de pagamento inválido.");
    }

}