package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Tema extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;

    @ManyToMany(mappedBy="listaTema")
    private List<Jogo> listaJogo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tema other = (Tema) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    public List<Jogo> getListaJogo() {
        return listaJogo;
    }

    public void setListaJogo(List<Jogo> listaJogo) {
        this.listaJogo = listaJogo;
    }

    
}