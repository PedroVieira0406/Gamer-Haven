package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.CompraService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CompraResourceTest {

    @Inject
    CompraService compraService;

    private String funcionarioToken;
    private String clienteToken;
    @BeforeEach
    public void setup() {
        funcionarioToken = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkRldXMtU2FtYSIsImdyb3VwcyI6WyJGdW5jaW9uYXJpbyJdLCJsb2duSWQiOiI1IiwiZXhwIjoxODI2Mzg0ODE4LCJpYXQiOjE3MTgzODQ4MTgsImp0aSI6IjA5ZDg1MDJkLTZlNjEtNDcwMy04MWUzLTdkODUyYWE1YTlmMSJ9.Yev3ZX8tvUjYICiBP_d_V6_6TWvjFprNCmHnC4y4vyXUHzlWoYPyDiHtXqvypPfZeeJ8gqPe75rZekG_QpUaeJq6daFIA5AkkAfwy4MVf76IwNAiMy2IgOl6qZI4J-0fabpHgc9ETdxTaL-YuybLlLXKkRKzzaikvFQAWh7HDV5jSlQQUia_m_7FTUwRTGjMNLm_vEllV3yYXFBpjOm-jHj-GhfjBT1L_qW8ksNrNO55LPu7W0GNmF2XZiXP0-1hW9Ca079xoMnnLVqYY1C4tZnDphW0CAo03DqXuF_JRfgj83XZvb3OgVVLkLCFOtcdJejXv3VZeqfwyJ1mySZabg ";
        clienteToken= "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkNyaWEwMSIsImdyb3VwcyI6WyJDbGllbnRlIl0sImxvZ25JZCI6IjEiLCJleHAiOjE4MjYzODQwMjUsImlhdCI6MTcxODM4NDAyNSwianRpIjoiZDA1ZjIzYzItYjg1Mi00YTMyLTkzMjktODg2ZjNlOTI4NjI1In0.kHXWKBlgs-LXH0Qrtiwpa0YFKrBEH5vpQRQFu3V-Z_8oiID_9RTkWP3VtXk_x3shjeDSo6jEr6vXioWBlB8BhxCB3PGFZkDY0FW8pzenD_yF9M4BvckXVryc8nG4Rfm1-0f5BX43qH-AvOWiVpdmuu9onUl1QYbfzXk6-6MtYwSNzdByL-6_r8M4VS-W6fVsvVFJ4F11DUBPVkDBLW2J87KykwyfSs6gzjM5cFJZf9eaYboXXP9cBSXZSw3H-pry1hNN9ECcMvQXqUQ6TOc1761f_dmkzHDy1741xzS4xRKUHfdpIe2m229iE5ChOPnh8jUk7CmLHYbA3x-4v-iATA ";
    }
    @Test
    public void findAllTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/compras")
            .then()
            .statusCode(200)
            .body("id", hasItem(1)); // Verifica se há pelo menos um item com ID igual a 1
    }

    @Test
    public void findByIdTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/compras/search/id/1")
            .then()
            .statusCode(200)
            .body("id", is(1)); // Verifica se o ID do item retornado é igual a 1
    }

    @Test
    public void findByJogoTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/compras/search/jogo/2")
            .then()
            .statusCode(200)
            .body("jogo", everyItem(is("Valorant"))); // Verifica se todos os itens têm idJogo igual ao jogo 2 "Valorant"
    }

    @Test
    public void findByDonoTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/compras/search/dono/1")
            .then()
            .statusCode(200)
            .body("dono", everyItem(is("Cria01"))); // Verifica todos os itens que têm Dono igual ao username do cliente 1 "Cria01"
    }

    @Test
    public void findByPaganteTest() {
        given()
        .header("Authorization", clienteToken)
            .when()
            .get("/compras/search/pagante/1")
            .then()
            .statusCode(200)
            .body("pagante", everyItem(is("Cria01"))); // Verifica todos os itens que têm Pagante igual ao username do cliente 1 "Cria01"
    }
}