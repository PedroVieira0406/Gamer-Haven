package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento> {


    public PanacheQuery<Pagamento> findByPagante(Long idCliente) {
        if (idCliente == null) {
            return null;
        }
        return find("cliente.id", idCliente);
    }
}