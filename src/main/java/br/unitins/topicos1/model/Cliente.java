package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {

    @Column(nullable = false)
    private Integer idade;

    @ManyToMany
    @JoinTable (name="biblioteca",
    joinColumns= @JoinColumn(name="idCliente"),
    inverseJoinColumns = @JoinColumn(name="idJogo"))
    private List<Jogo> biblioteca;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Cartao> cartoes;

    public List<Jogo> getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(List<Jogo> biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((idade == null) ? 0 : idade.hashCode());
        result = prime * result + ((biblioteca == null) ? 0 : biblioteca.hashCode());
        result = prime * result + ((cartoes == null) ? 0 : cartoes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (idade == null) {
            if (other.idade != null)
                return false;
        } else if (!idade.equals(other.idade))
            return false;
        if (biblioteca == null) {
            if (other.biblioteca != null)
                return false;
        } else if (!biblioteca.equals(other.biblioteca))
            return false;
        if (cartoes == null) {
            if (other.cartoes != null)
                return false;
        } else if (!cartoes.equals(other.cartoes))
            return false;
        return true;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
 
}
