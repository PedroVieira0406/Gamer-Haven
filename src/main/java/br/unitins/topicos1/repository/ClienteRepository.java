package br.unitins.topicos1.repository;


import br.unitins.topicos1.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public PanacheQuery<Cliente> findByNome(String nome) {
        if(nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%");
    }
    public PanacheQuery<Cliente> findByEmail(String email) {
        if(email == null)
            return null;
        return find("UPPER(email) LIKE ?1", "%"+ email.toUpperCase() + "%");
    }

    public Cliente findByNomeCompleto(String nome) {
        return find("UPPER(nome) = ?1",  nome.toUpperCase() ).firstResult();
    }
    public Cliente findByUsernameAndSenha(String name, String senha) {
        return find("login.name = ?1 AND login.senha = ?2", name, senha).firstResult();
    }
}
