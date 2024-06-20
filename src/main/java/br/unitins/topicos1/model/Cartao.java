package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cartao  extends DefaultEntity{

    @Enumerated(EnumType.STRING)
    private CartaoMarca CartaoMarca;

    @Column(nullable = false)
    private String nCartao;

    @Column(nullable = false)
    private Integer mesValid;

    @Column(nullable = false)
    private Integer anoValid;

    @Column(nullable = false)
    private String numSeguranca;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String pais;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;


    public CartaoMarca getCartaoMarca() {
        return CartaoMarca;
    }

    public void setCartaoMarca(CartaoMarca cartaoMarca) {
        CartaoMarca = cartaoMarca;
    }

    public String getnCartao() {
        return nCartao;
    }

    public void setnCartao(String nCartao) {
        this.nCartao = nCartao;
    }

    public Integer getMesValid() {
        return mesValid;
    }

    public void setMesValid(Integer mesValid) {
        this.mesValid = mesValid;
    }

    public Integer getAnoValid() {
        return anoValid;
    }

    public void setAnoValid(Integer anoValid) {
        this.anoValid = anoValid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((CartaoMarca == null) ? 0 : CartaoMarca.hashCode());
        result = prime * result + ((nCartao == null) ? 0 : nCartao.hashCode());
        result = prime * result + ((mesValid == null) ? 0 : mesValid.hashCode());
        result = prime * result + ((anoValid == null) ? 0 : anoValid.hashCode());
        result = prime * result + ((numSeguranca == null) ? 0 : numSeguranca.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
        result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
        Cartao other = (Cartao) obj;
        if (CartaoMarca != other.CartaoMarca)
            return false;
        if (nCartao == null) {
            if (other.nCartao != null)
                return false;
        } else if (!nCartao.equals(other.nCartao))
            return false;
        if (mesValid == null) {
            if (other.mesValid != null)
                return false;
        } else if (!mesValid.equals(other.mesValid))
            return false;
        if (anoValid == null) {
            if (other.anoValid != null)
                return false;
        } else if (!anoValid.equals(other.anoValid))
            return false;
        if (numSeguranca == null) {
            if (other.numSeguranca != null)
                return false;
        } else if (!numSeguranca.equals(other.numSeguranca))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (sobrenome == null) {
            if (other.sobrenome != null)
                return false;
        } else if (!sobrenome.equals(other.sobrenome))
            return false;
        if (cidade == null) {
            if (other.cidade != null)
                return false;
        } else if (!cidade.equals(other.cidade))
            return false;
        if (cep == null) {
            if (other.cep != null)
                return false;
        } else if (!cep.equals(other.cep))
            return false;
        if (pais == null) {
            if (other.pais != null)
                return false;
        } else if (!pais.equals(other.pais))
            return false;
        return true;
    }

    public String getNumSeguranca() {
        return numSeguranca;
    }

    public void setNumSeguranca(String numSeguranca) {
        this.numSeguranca = numSeguranca;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

