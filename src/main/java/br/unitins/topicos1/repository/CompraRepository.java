package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Compra;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompraRepository implements PanacheRepository<Compra> {

    public PanacheQuery<Compra> findByDono(Long idDono) {
        if (idDono == null) {
            return null;
        }
        return find("dono.id", idDono);
    }

    public PanacheQuery<Compra> findByPagante(Long idPagante) {
        if (idPagante == null) {
            return null;
        }
        return find("pagante.id", idPagante);
    }

    public PanacheQuery<Compra> findByJogo(Long idJogo) {
        if (idJogo == null) {
            return null;
        }
        return find("jogo.id", idJogo);
    }

    public PanacheQuery<Compra> findByPagamento(Long idPagamento) {
        if (idPagamento == null) {
            return null;
        }
        return find("pagamento.id", idPagamento);
    }
}