package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Compra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompraRepository implements PanacheRepository<Compra> {

    public List<Compra> findByDono(Long idDono) {
        if (idDono == null) {
            return null;
        }
        return find("dono.id", idDono).list();
    }

    public List<Compra> findByPagante(Long idPagante) {
        if (idPagante == null) {
            return null;
        }
        return find("pagante.id", idPagante).list();
    }

    public List<Compra> findByJogo(Long idJogo) {
        if (idJogo == null) {
            return null;
        }
        return find("jogo.id", idJogo).list();
    }

    public Compra findByPagamento(Long idPagamento) {
        if (idPagamento == null) {
            return null;
        }
        return find("pagamento.id", idPagamento).firstResult();
    }
}