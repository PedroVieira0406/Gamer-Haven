package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.JogoDTO;
import br.unitins.topicos1.dto.JogoResponseDTO;
import br.unitins.topicos1.service.JogoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class JogoResourceTest {
 
    @Inject
    public JogoService jogoService;

    private String funcionarioToken;
    
    @BeforeEach
    public void setup() {
        funcionarioToken = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkRldXMtU2FtYSIsImdyb3VwcyI6WyJGdW5jaW9uYXJpbyJdLCJsb2duSWQiOiI1IiwiZXhwIjoxODI2Mzg0ODE4LCJpYXQiOjE3MTgzODQ4MTgsImp0aSI6IjA5ZDg1MDJkLTZlNjEtNDcwMy04MWUzLTdkODUyYWE1YTlmMSJ9.Yev3ZX8tvUjYICiBP_d_V6_6TWvjFprNCmHnC4y4vyXUHzlWoYPyDiHtXqvypPfZeeJ8gqPe75rZekG_QpUaeJq6daFIA5AkkAfwy4MVf76IwNAiMy2IgOl6qZI4J-0fabpHgc9ETdxTaL-YuybLlLXKkRKzzaikvFQAWh7HDV5jSlQQUia_m_7FTUwRTGjMNLm_vEllV3yYXFBpjOm-jHj-GhfjBT1L_qW8ksNrNO55LPu7W0GNmF2XZiXP0-1hW9Ca079xoMnnLVqYY1C4tZnDphW0CAo03DqXuF_JRfgj83XZvb3OgVVLkLCFOtcdJejXv3VZeqfwyJ1mySZabg ";
    }
    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/jogos")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("Mario")));
    }

    @Test
    public void findByIdTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/jogos/search/id/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/jogos/search/nome/fnaf")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("fnaf")));
    }


    @Test
    public void updateTest() {
        JogoDTO dto = new JogoDTO("Mario", 150.01f, 0.0f, 2, 1L, Arrays.asList(1L, 2L), Arrays.asList(1L));

        given()
        .header("Authorization", funcionarioToken)
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 1)
            .put("/jogos/update/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {
        JogoResponseDTO response = jogoService.create(new JogoDTO("Lol", 1.00f, 1.00f, 1, 2L, Arrays.asList(1L, 2L), Arrays.asList(1L)));
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .pathParam("id",response.id())
            .delete("/jogos/delete/{id}")
            .then()
            .statusCode(204);
        jogoService.delete(response.id());
        }
}