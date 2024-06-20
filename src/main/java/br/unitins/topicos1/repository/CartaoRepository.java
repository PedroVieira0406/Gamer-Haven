package br.unitins.topicos1.repository;


import br.unitins.topicos1.model.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao> {

    public PanacheQuery<Cartao> findByDono(Long idDono) {
        if (idDono == null) {
            return null;
        }
        return find("cliente.id", idDono);
    }
    public Cartao findByNumeroECliente(String numero, Long idCliente) {
        if (numero == null || idCliente == null) {
            return null;
        }
        return find("nCartao = ?1 and cliente.id = ?2", numero, idCliente).firstResult();
    }
}
