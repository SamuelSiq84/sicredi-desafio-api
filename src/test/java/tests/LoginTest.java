package tests;

import baseURL.ApiEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class LoginTest {

    @BeforeEach
    public void Setup(){
        RestAssured.baseURI = ApiEndpoints.BASE_URL;

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Autenticação de login por username e password")
    @Description("Validar autenticação de USER por username e password")

    public void LoginComCredencialValida(){
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
    @Story("Autenticação de login por username e password")
    @Description("Validar erro na autenticação de USER com password inválido")

    public void LoginComCredencialInvalida(){
        Setup();
        String JsonBody = "{\"username\":\"emilys\",\r\n" +
                "\"password\":\"123456\"}";

        given().contentType("application/json")
                .body(JsonBody)
                .when()
                .post(ApiEndpoints.AUTH_LOGIN)
                .then()
                .log().all()
                .statusCode(400)
                .assertThat().body("message",is("Invalid credentials"));
    }
}
