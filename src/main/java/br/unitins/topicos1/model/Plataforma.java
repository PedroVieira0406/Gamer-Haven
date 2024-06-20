package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Plataforma extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setPlataforma(String nome) {
        this.nome = nome;
    }

    @ManyToMany(mappedBy="listaPlataforma")
    private List<Jogo> listaJogo;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((listaJogo == null) ? 0 : listaJogo.hashCode());
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
        Plataforma other = (Plataforma) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (listaJogo == null) {
            if (other.listaJogo != null)
                return false;
        } else if (!listaJogo.equals(other.listaJogo))
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