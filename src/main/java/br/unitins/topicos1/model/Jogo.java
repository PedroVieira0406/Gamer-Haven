package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Jogo extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 8, nullable = false)
    private Float preco;

    @Column(length = 8, nullable = false)
    private Float desconto;

    @Enumerated(EnumType.STRING)
    private FaixaEtaria faixaEtaria;

    @ManyToOne
    @JoinColumn(name = "empresa_id") 
    private Empresa empresa;

    @ManyToMany
    @JoinTable (name="jogo_plataforma",
    joinColumns= @JoinColumn(name="idjogo"),
    inverseJoinColumns = @JoinColumn(name="idplataforma") )
    private List<Plataforma> listaPlataforma;

    @ManyToMany
    @JoinTable (name="jogo_tema",
    joinColumns= @JoinColumn(name="idjogo"),
    inverseJoinColumns = @JoinColumn(name="idtema") )
    private List<Tema> listaTema;

    private String nomeImagem;
    
    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }
    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public FaixaEtaria getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(FaixaEtaria faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Plataforma> getListaPlataforma() {
        return listaPlataforma;
    }

    public void setListaPlataforma(List<Plataforma> listaPlataforma) {
        this.listaPlataforma = listaPlataforma;
    }

    public List<Tema> getListaTema() {
        return listaTema;
    }

    public void setListaTema(List<Tema> listaTema) {
        this.listaTema = listaTema;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((preco == null) ? 0 : preco.hashCode());
        result = prime * result + ((faixaEtaria == null) ? 0 : faixaEtaria.hashCode());
        result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
        result = prime * result + ((listaPlataforma == null) ? 0 : listaPlataforma.hashCode());
        result = prime * result + ((listaTema == null) ? 0 : listaTema.hashCode());
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
        Jogo other = (Jogo) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (preco == null) {
            if (other.preco != null)
                return false;
        } else if (!preco.equals(other.preco))
            return false;
        if (faixaEtaria != other.faixaEtaria)
            return false;
        if (empresa == null) {
            if (other.empresa != null)
                return false;
        } else if (!empresa.equals(other.empresa))
            return false;
        if (listaPlataforma == null) {
            if (other.listaPlataforma != null)
                return false;
        } else if (!listaPlataforma.equals(other.listaPlataforma))
            return false;
        if (listaTema == null) {
            if (other.listaTema != null)
                return false;
        } else if (!listaTema.equals(other.listaTema))
            return false;
        return true;
    }
}