package tests;

import baseURL.ApiEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProdutsTest {

    @BeforeEach
    public void Setup(){
        RestAssured.baseURI = ApiEndpoints.BASE_URL;

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar produtos com autenticação")
    @Description("Validar busca de produtos com autenticação válida")

    public void BuscarProdutosComAutenticação(){
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

        System.out.println("AccessToken válido: " + token);

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
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar produtos com autenticação")
    @Description("Validar busca de produtos com autenticação inválida")

    public void BuscarProdutosComAutenticaçãoInvalida(){
        Setup();
        String JsonBody = "{\"username\":\"emilys\",\r\n" +
                "\"password\":\"123456\"}";
        String token =
                given().contentType("application/json")
                        .body(JsonBody)

                        .when().post(ApiEndpoints.AUTH_LOGIN)
                        .then()
                        .log().all()
                        .statusCode(400)
                        .assertThat().body("message",is("Invalid credentials"))
                        .extract().path("accessToken");

        Response response = given().contentType("application/json")
                .header("Authorization","Baerer " + token)
                .log().all()
                .when()
                .get(ApiEndpoints.AUTH_PRODUCTS)
                .then()
                .log().all()
                .statusCode(401)
                .assertThat().body("message",is("Invalid/Expired Token!"))
                .extract().response();

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Criação de Produto")
    @Description("Validar criação de produto com sucesso")

    public void CriacaoProdutoComSucesso(){
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
    @Severity(SeverityLevel.NORMAL)
    @Story("Criação de Produto")
    @Description("Criação de produto com title inválido")

    public void ErroNaCriacaoDeProdutoComTitleInvalido(){
        Setup();
        String JsonBody = "{\n\t\"title\":123@%, \n\t\"description\": \"Mega Discount, Impression of A...\",\n\t\"price\": 13, \n\t\"discountPercentage\": 8.4,\n\t\"rating\": 4.26,\n\t\"stock\": 65,\n\t\"brand\": \"Impression of Acqua Di Gio\",\n\t\"category\": \"fragrances\",\n\t\"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\"\n}";

        given()
                .contentType("application/json")
                .log().all()
                .body(JsonBody)
                .when()
                .post(ApiEndpoints.PRODUCTS_ADD)
                .then()
                .log().all()
                .statusCode(400)
        ;
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar por produtos")
    @Description("Validar busca de todos os produtos")

    public void BuscarPorTodosOsProdutos(){
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
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar por produtos")
    @Description("Validar buscar de produto por ID")

    public void BuscarPorProdutoPeloID(){
        Setup();

        given()
                .contentType("application/json")
                .log().all()
                .when()
                .get(ApiEndpoints.PRODUCTS + "/{id}",20)
                .then().log().all()
                .statusCode(200)
                .assertThat().body("id",is(20))
                .assertThat().body("title",is("Cooking Oil"))
        ;
    }
}
