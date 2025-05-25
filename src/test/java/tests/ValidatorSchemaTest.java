package tests;

import baseURL.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static baseURL.ApiEndpoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ValidatorSchemaTest {

    @BeforeEach
    public void Setup(){
        RestAssured.baseURI = ApiEndpoints.BASE_URL;
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

    }
    @Test
    public void ValidatorSchemaUsers() {
        Setup();

        given()

                .contentType("application/json")
                .log().all()
                .when()
                .get(USERS)
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/usersSchema.json"));
    }
    @Test
    public void ValidatorSchemaUserID() {
        Setup();

        String JsonBody = "{" +
                "id\":6," +
                "firstName\":\"Olivia\"," +
                "lastName\":\"Wilson\"," +
                "maidenName\":\"\"," +
                "age\":22," +
                "gender\":\"female\"," +
                "email\":\"olivia.wilson@x.dummyjson.com\"," +
                "phone\":\"+91 607-295-6448\"," +
                "username\":\"oliviaw\"," +
                "password\":\"oliviawpass\"," +
                "birthDate\":\"2002-4-20\"," +
                "image\":\"https://dummyjson.com/icon/oliviaw/128\"," +
                "bloodGroup\":\"B+\"," +
                "height\":182.61," +
                "weight\":58," +
                "eyeColor\":\"Hazel\"," +
                "hair\":{" +
                "color\":\"Gray\"," +
                "type\":\"Curly\"" +
                "}," +
                "ip\":\"249.178.112.207\"," +
                "address\":{" +
                "address\":\"547 First Street\"," +
                "city\":\"Fort Worth\"," +
                "state\":\"Tennessee\"," +
                "stateCode\":\"TN\"," +
                "postalCode\":\"83843\"," +
                "coordinates\":{" +
                "lat\":75.32627," +
                "lng\":-26.15285" +
                "}," +
                "country\":\"United States\"" +
                "}," +
                "macAddress\":\"9c:7f:ea:34:18:19\"," +
                "university\":\"University of North Carolina--Chapel Hill\"," +
                "bank\":{" +
                "cardExpire\":\"05/28\"," +
                "cardNumber\":\"6771923832947881\"," +
                "cardType\":\"Diners Club International\"," +
                "currency\":\"BRL\"," +
                "iban\":\"V6H0O5OE3Q4JVKWDTYWZABMD\"" +
                "}," +
                "company\":{" +
                "department\":\"Product Management\"," +
                "name\":\"Pfannerstill Inc\"," +
                "title\":\"Research Analyst\"," +
                "address\":{" +
                "address\":\"425 Sixth Street\"," +
                "city\":\"Indianapolis\"," +
                "state\":\"Oklahoma\"," +
                "stateCode\":\"OK\"," +
                "postalCode\":\"74263\"," +
                "coordinates\":{" +
                "lat\":74.986644," +
                "lng\":-132.916888" +
                "}," +
                "country\":\"United States\"" +
                "}" +
                "}," +
                "ein\":\"921-709\"," +
                "ssn\":\"836-772-168\"," +
                "userAgent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.3 Safari/605.1.15\"," +
                "crypto\":{" +
                "coin\":\"Bitcoin\"," +
                "wallet\":\"0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a\"," +
                "network\":\"Ethereum (ERC20)\"" +
                "}," +
                "role\":\"moderator\"" +
                "}";

        given().contentType("application/json")
                .body(JsonBody)
                .log().all()
                .when()
                .get(USERS + "{id}",6)
                .then()
                .log().all()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/userSchema.json"));
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


}
