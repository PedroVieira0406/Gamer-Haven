package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ClienteResourceTest {
 
    @Inject
    public ClienteService clienteService;

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
        .header("Authorization", clienteToken)
            .when()
            .get("/clientes")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("João Silva")));
    }

    @Test
    public void findByIdTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/clientes/search/id/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
        .header("Authorization", clienteToken)
            .when()
            .get("/clientes/search/nome/Roberto Pereira")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("Roberto Pereira")));
    }

    @Test
    public void findByEmailTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/clientes/search/email/joao@gmail.com")
            .then()
            .statusCode(200)
            .body("gmail", everyItem(is("joao@gmail.com")));
    }

    @Test
    public void updateTest() {

        ClienteDTO dto = new ClienteDTO("pepeu","Pedro Oliveira", "pedro@gmail.com", "45678912310", "pipo01", 23);
        given()
        .header("Authorization", clienteToken)
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 2)
            .put("/clientes/update/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {
        ClienteResponseDTO response = clienteService.create(new ClienteDTO("Mari999","Maria Souza", "maria@example.com", "98765432110", "Mar Amar", 17));
        given()
        .header("Authorization", clienteToken)
            .when()
            .pathParam("id",response.id())
            .delete("/clientes/delete/{id}")
            .then()
            .statusCode(204);
        clienteService.delete(response.id());
        }
}