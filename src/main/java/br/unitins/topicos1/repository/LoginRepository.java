package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Login;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginRepository implements PanacheRepository<Login> {

    public Login findByUsername(String name) {
        return find("UPPER(username) = ?1", name).firstResult();
    }

}