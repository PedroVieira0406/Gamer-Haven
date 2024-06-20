package br.unitins.topicos1.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Compra extends DefaultEntity {

    @Column(length = 10, nullable = false)
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "idPagante")
    private Cliente pagante;

    @ManyToOne
    @JoinColumn(name = "idDono")
    private Cliente dono;

    @ManyToOne
    @JoinColumn(name = "idJogo")
    private Jogo jogo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPagamento")
    private Pagamento pagamento;

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Cliente getPagante() {
        return pagante;
    }

    public void setPagante(Cliente pagante) {
        this.pagante = pagante;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}