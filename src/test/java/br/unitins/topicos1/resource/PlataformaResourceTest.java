package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.PlataformaDTO;
import br.unitins.topicos1.dto.PlataformaResponseDTO;
import br.unitins.topicos1.service.PlataformaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class PlataformaResourceTest {
 
    @Inject
    public PlataformaService plataformaService;

    private String funcionarioToken;
    
    @BeforeEach
    public void setup() {
        funcionarioToken = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJ1bml0aW5zLWp3dCIsInN1YiI6IkRldXMtU2FtYSIsImdyb3VwcyI6WyJGdW5jaW9uYXJpbyJdLCJsb2duSWQiOiI1IiwiZXhwIjoxODI2Mzg0ODE4LCJpYXQiOjE3MTgzODQ4MTgsImp0aSI6IjA5ZDg1MDJkLTZlNjEtNDcwMy04MWUzLTdkODUyYWE1YTlmMSJ9.Yev3ZX8tvUjYICiBP_d_V6_6TWvjFprNCmHnC4y4vyXUHzlWoYPyDiHtXqvypPfZeeJ8gqPe75rZekG_QpUaeJq6daFIA5AkkAfwy4MVf76IwNAiMy2IgOl6qZI4J-0fabpHgc9ETdxTaL-YuybLlLXKkRKzzaikvFQAWh7HDV5jSlQQUia_m_7FTUwRTGjMNLm_vEllV3yYXFBpjOm-jHj-GhfjBT1L_qW8ksNrNO55LPu7W0GNmF2XZiXP0-1hW9Ca079xoMnnLVqYY1C4tZnDphW0CAo03DqXuF_JRfgj83XZvb3OgVVLkLCFOtcdJejXv3VZeqfwyJ1mySZabg ";
    }

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/plataformas")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("Xbox")));
    }

    @Test
    public void findByIdTest() {
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .get("/plataformas/search/id/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/plataformas/search/nome/Playstation")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("Playstation")));
    }

    @Test
    public void updateTest() {
        PlataformaDTO dto = new PlataformaDTO("Tablet");
        given()
        .header("Authorization", funcionarioToken)
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 4)
            .put("/plataformas/update/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    public void deleteTest() {
        PlataformaResponseDTO response = plataformaService.create(new PlataformaDTO("Iphone"));
        given()
        .header("Authorization", funcionarioToken)
            .when()
            .pathParam("id",response.id())
            .delete("/plataformas/delete/{id}")
            .then()
            .statusCode(204);
        plataformaService.delete(response.id());
        }
}