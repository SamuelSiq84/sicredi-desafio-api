package tests;

import baseURL.ApiEndpoints;

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
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    }

    @Test
    public void GetUsers() {
        Setup();

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(USERS + "/{id}",1)
        .then()
                .log().all()
                .statusCode(200)
                .assertThat().body("username",is("emilys"))
                .assertThat().body("password",is("emilyspass"));
    }

    @Test
    public void PostAuthLogin(){
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
    public void GetAuthProducts(){
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
                .statusCode(200)
                ;
    }
    @Test
    public void PostAuthLoginForGetAuthProducts(){ //alternativo
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
    public void PostProductsAdd(){
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
    public void GetProducts(){
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
    public void GetProductsID(){
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
