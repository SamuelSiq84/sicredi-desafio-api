package tests;

import baseURL.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static baseURL.ApiEndpoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ValidatorSchemaTest {

    @BeforeEach
    public void Setup(){
        RestAssured.baseURI = ApiEndpoints.BASE_URL;
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

    }
    @Test
    public void UserSchemaValidatorTest(){

        String JsonBodyUser = "{\"username\":\"emilys\",\r\n" +
                "\"password\":\"emilyspass\"}";

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(JsonBodyUser);
        when()
                .post(ApiEndpoints.AUTH_LOGIN)
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/UserSchema.json"));
    }
    @Test
    public void userSchemaNotUsername(){

        String JsonBodyUser = "{\"password\":\"emilyspass\"}";

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(JsonBodyUser)
                .when()
                .post(AUTH_LOGIN)
                .then()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/UserSchema.json"));
    }
    @Test
    public void productSchemaValidatorTest(){

        String JsonBody = "{\n\t\"title\":\"Perfume Oil\", \n\t\"description\": \"Mega Discount, Impression of A...\",\n\t\"price\": 13, \n\t\"discountPercentage\": 8.4,\n\t\"rating\": 4.26,\n\t\"stock\": 65,\n\t\"brand\": \"Impression of Acqua Di Gio\",\n\t\"category\": \"fragrances\",\n\t\"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\"\n}";

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(JsonBody)
                .when()
                .post(PRODUCTS_ADD)
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/productSchema.json"));
    }
    @Test
    public void productSchemaNotTitle(){
        String JsonBody = "{\n\t\"description\": \"Mega Discount, Impression of A...\",\n\t\"price\": 13, \n\t\"discountPercentage\": 8.4,\n\t\"rating\": 4.26,\n\t\"stock\": 65,\n\t\"brand\": \"Impression of Acqua Di Gio\",\n\t\"category\": \"fragrances\",\n\t\"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\"\n}";

        given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(JsonBody)
                .when()
                .post(PRODUCTS_ADD)
                .then()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/productSchema.json"));

    }

}
