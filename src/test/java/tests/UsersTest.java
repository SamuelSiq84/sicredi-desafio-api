package tests;

import baseURL.ApiEndpoints;


import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UsersTest extends ApiEndpoints{

    @BeforeEach
    public void Setup(){
        RestAssured.baseURI = ApiEndpoints.BASE_URL;

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar por USERS")
    @Description("Validar retornar de todos os USERS")

    public void BuscarTodosUsers() {
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
    @Story("Buscar por USERS")
    @Description("Validar retorno de USER por ID")

    public void BuscarUserPorID() {
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

}
