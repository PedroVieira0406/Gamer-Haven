package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CartaoMarca {
    VISA(1, "Visa"),
    MASTERCARD(2, "MasterCard"),
    JCB(3, "JCB");

    private int id;
    private String nome;

    CartaoMarca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static CartaoMarca valueOf(Integer id) throws IllegalArgumentException {
        for (CartaoMarca CartaoMarca : CartaoMarca.values()) {
            if (CartaoMarca.id == id)
                return CartaoMarca;
        }
        throw new IllegalArgumentException("id de tipo de cartão inválido.");
    }
}