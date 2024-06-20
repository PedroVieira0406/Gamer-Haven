package br.unitins.topicos1.repository;


import br.unitins.topicos1.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {

    public PanacheQuery<Funcionario> findByNome(String nome) {
        if(nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%");
    }

    public PanacheQuery<Funcionario> findByCargo(String cargo) {
        if(cargo == null)
            return null;
        return find("UPPER(nome) LIKE ?1", "%"+ cargo.toUpperCase() + "%");
    }

    public Funcionario findByNomeCompleto(String nome) {
        return find("UPPER(nome) = ?1",  nome.toUpperCase() ).firstResult();
    }
    public Funcionario findByUsernameAndSenha(String username, String senha) {
        return find("login.name = ?1 AND login.senha = ?2", username, senha).firstResult();
    }

    
}
