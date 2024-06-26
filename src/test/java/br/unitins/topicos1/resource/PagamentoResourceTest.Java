package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.PagamentoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class PagamentoResourceTest {

    @Inject
    PagamentoService PagamentoService;

    private String funcionarioToken;
    @BeforeEach
    public void setup() {
        funcionarioToken = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkRldXMtU2FtYSIsImdyb3VwcyI6WyJGdW5jaW9uYXJpbyJdLCJsb2duSWQiOiI1IiwiZXhwIjoxODI2Mzg0ODE4LCJpYXQiOjE3MTgzODQ4MTgsImp0aSI6IjA5ZDg1MDJkLTZlNjEtNDcwMy04MWUzLTdkODUyYWE1YTlmMSJ9.Yev3ZX8tvUjYICiBP_d_V6_6TWvjFprNCmHnC4y4vyXUHzlWoYPyDiHtXqvypPfZeeJ8gqPe75rZekG_QpUaeJq6daFIA5AkkAfwy4MVf76IwNAiMy2IgOl6qZI4J-0fabpHgc9ETdxTaL-YuybLlLXKkRKzzaikvFQAWh7HDV5jSlQQUia_m_7FTUwRTGjMNLm_vEllV3yYXFBpjOm-jHj-GhfjBT1L_qW8ksNrNO55LPu7W0GNmF2XZiXP0-1hW9Ca079xoMnnLVqYY1C4tZnDphW0CAo03DqXuF_JRfgj83XZvb3OgVVLkLCFOtcdJejXv3VZeqfwyJ1mySZabg ";
    }
    @Test
    public void findAllTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/pagamentos")
            .then()
            .statusCode(200)
            .body("id", hasItem(1)); // Verifica se há pelo menos um item com ID igual a 1
    }

    @Test
    public void findByIdTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/pagamentos/search/id/1")
            .then()
            .statusCode(200)
            .body("id", is(1)); // Verifica se o ID do item retornado é igual a 1
    }

    @Test
    public void findByClienteTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/pagamentos/search/cliente/1")
            .then()
            .statusCode(200)
            .body("clienteId",hasItem(is(1))); // Verifica todos os itens que têm Dono igual ao username do cliente 1 "Cria01"
    }
}