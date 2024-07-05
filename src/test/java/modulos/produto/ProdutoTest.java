package modulos.produto;


import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API do modulo de Produto")

public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        //Configurndo os dados da lojinha API -endpoint
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";



        //Obter o token do usuário Admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");


    }
    @Test
    @DisplayName("Validar que o valor do produto não pode ser igual a 0.00")
    public void testValidarLimitesProibidosValorProduto() {

        // Tentar inserir um produto com valor 0.00 e validar a mensagem de erro foi apresentada
        // Status Code 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComUmValorIgualA(0.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }


        @Test
        @DisplayName("Validar os inserção de produto com  valor permitido")
        public void testValidarOsInsercaoDeProduto() {
            //Tentar inserir um produto com valor maior que 0.00 e validar a mensagem de sucesso foi apresentada
            //        // Status Code 200

            given()
                    .contentType(ContentType.JSON)
                    .header("token", this.token)
                    .body (ProdutoDataFactory.criarProdutoComUmValorIgualA(1.00))
            .when()
                    .post("/v2/produtos")
            .then()
                    .assertThat()
                    .body("error", equalTo(""))
                    .statusCode(201);


        }


              }
