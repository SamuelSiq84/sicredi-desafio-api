package tests;

import baseURL.ApiEndpoints;


import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAPITest extends ApiEndpoints{


    @BeforeEach


    public void Setup(){
        RestAssured.baseURI = ApiEndpoints.BASE_URL;

    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validar GET lista de USERS")
    @Description("Validar retornar todos os USERS")

    public void CT001_ValidarMetodoGETUsers() {
        Setup();

        given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .log().all()
                .when()
                .get(USERS)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validar GET lista de USERS")
    @Description("Validar retorno do método GET passando ID de USERS")

    public void CT002_ValidarMetodoGETIDUsers() {//alternativo
        Setup();

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(USERS + "/{id}",6)
        .then()
                .log().all()
                .statusCode(200)
                .assertThat().body("id",is(6))
                .assertThat().body("username",is("oliviaw"))
                .assertThat().body("password",is("oliviawpass"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validar POST autenticação de login do USER")
    @Description("Validar retorno do método Authorization Login")

    public void CT003_ValidarMetodoPOSTAuthorizationLoginUser(){
        Setup();
        String JsonBody = "{\"username\":\"emilys\",\r\n" +
                "\"password\":\"emilyspass\"}";

        given().contentType("application/json")
                .body(JsonBody)
        .when()
                .post(ApiEndpoints.AUTH_LOGIN)
        .then()
                .log().all()
                .statusCode(200)
                .assertThat().body("username",is("emilys"))
                .assertThat().body("id",is(1));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validar GET Products")
    @Description("Validar retorno do método consultar produtos passando o token")

    public void CT004_ValidarMetodoGETProductsAuthorizationLoginUSER(){
        Setup();
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MzgwOTI4NzEsImV4cCI6MTczODA5NjQ3MX0.nG4Sr38B4lO4Gu9Xx1RafsRO6xZugdOq22hV5DnRYCI";
        String authToken = "Baerer " + token;
        given()
                .contentType("application/json")
                .header("Authorization", authToken)
                .log().all()
        .when()
                .get(ApiEndpoints.AUTH_PRODUCTS)
        .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validar GET Token e POST Products")
    @Description("Validar retorno do método GET com o token e consultar produtos passando o token")

    public void CT005_ValidarPOSTAuthorizationLoginParaGETAuthProducts(){ //alternativo
        Setup();
        String JsonBody = "{\"username\":\"emilys\",\r\n" +
           "\"password\":\"emilyspass\"}";
        String token =
        given().contentType("application/json")
                .body(JsonBody)

        .when().post(ApiEndpoints.AUTH_LOGIN)
        .then()
                .log().all()
                .extract().path("accessToken");

        //System.out.println("AccessToken válido: " + token);

        Response response = given().contentType("application/json")
                .header("Authorization","Baerer " + token)
                .log().all()
        .when()
                .get(ApiEndpoints.AUTH_PRODUCTS)
        .then()
                .log().all()
                .statusCode(200)
                .extract().response()
                ;

        String accessToken = response.path("accessToken");
        long accessToken1 =
                given().
                when().get()
                .then().extract().jsonPath().getLong("accessToken");
    assertThat(
            when().get().then().extract().jsonPath().getLong("accessToken"),equalTo(token)
    );

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Validar POST adicionando produto")
    @Description("Validar retorno do método POST adicionar produto na lista passando na request body o JSON")

    public void CT006_ValidarPOSTProductAdd(){
        Setup();
        String JsonBody = "{\n\t\"title\":\"Perfume Oil\", \n\t\"description\": \"Mega Discount, Impression of A...\",\n\t\"price\": 13, \n\t\"discountPercentage\": 8.4,\n\t\"rating\": 4.26,\n\t\"stock\": 65,\n\t\"brand\": \"Impression of Acqua Di Gio\",\n\t\"category\": \"fragrances\",\n\t\"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\"\n}";

        given()
                .contentType("application/json")
                .log().all()
                .body(JsonBody)
        .when()
                .post(ApiEndpoints.PRODUCTS_ADD)
        .then()
                .log().all()
                .statusCode(201)
                .assertThat().body("title",is("Perfume Oil"))
                .assertThat().body("price",is(13))
                ;
    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Validar GET Products")
    @Description("Validar retorno do método GET a lista de produtos")

    public void CT007_ValidarGETProducts(){
        Setup();

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(ApiEndpoints.PRODUCTS)
        .then().log().all()
                .statusCode(200);

    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Validar GET Products")
    @Description("Validar retorno do método GET o title e price de um produto da lista")

    public void CT008_ValidarGETResponseIDTitleProduct(){
        Setup();

        given()
                .contentType("application/json")
                .log().all()
                .when()
                .get(ApiEndpoints.PRODUCTS)
                .then().log().all()
                .statusCode(200)
                .assertThat().body("products[19].id",is(20))
                .assertThat().body("products[19].title",is("Cooking Oil"))

        ;
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validar GET Products")
    @Description("Validar retorno do método GET passando na request o ID do produto e no response body o ID e Title")

    public void CT009_GetProductsID(){
        Setup();

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(ApiEndpoints.PRODUCTS_ID + "2")
        .then()
                .log().all()
                .statusCode(200)
                .assertThat().body("id",is(2))
                .assertThat().body("title",is("Eyeshadow Palette with Mirror"));
    }
}
