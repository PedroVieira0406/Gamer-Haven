package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagamento extends DefaultEntity {

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(nullable = false)
    private boolean status = false;

    @Column(nullable = false)
    private float valorCompra;

    @Column(nullable = false)
    private float valorPago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModoPagamento modoPagamento;

    @ManyToOne
    @JoinColumn(name = "cartao_id",nullable = true)
    private Cartao cartao;

    // Getters and Setters

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) {
        this.valorPago = valorPago;
    }

    public ModoPagamento getModoPagamento() {
        return modoPagamento;
    }

    public void setModoPagamento(ModoPagamento modoPagamento) {
        this.modoPagamento = modoPagamento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long idCliente) {
        this.clienteId = idCliente;
    }
}
