package be.helpper.users;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import jakarta.inject.Inject;
import jakarta.resource.spi.work.SecurityContext;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.*;
import static java.lang.String.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class UserResourceTest {
    @Test
    @TestSecurity(authorizationEnabled = false)
    void shouldNotGetUnknownUser() {
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .pathParam("id", Long.MAX_VALUE).
                when()
                .get("/api/users/{id}").
                then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void create() {
        given()
                .body("{\"voornaam\":\"test\", \"familienaam\":\"test\",\"email\":\"test@test.com\", \"wachtwoord\":\"test\", \"rol\":\"assistent\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/users/nieuweUser")
                .then()
                .statusCode(201);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldFindById() {
        given()
                .pathParam("id", 1)
                .when().get("/api/users/{id}")
                .then()
                .statusCode(200)
                .body("voornaam", is("Budgethouder"))
                .body("familienaam", is("Budgethouder"))
                .body("email", is("budgethouder@test.com"))
                .body("rol", is("budgethouder"));
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldNotFindByMaxLongId() {
        given()
                .pathParam("id", Long.MAX_VALUE)
                .when().get("/api/users/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "")
    void forbiddenFindById() {
        given()
                .pathParam("id", 1)
                .when().get("/api/users/{id}")
                .then()
                .statusCode(403);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldFindByEmail() {
        given()
                .queryParam("email", "budgethouder@test.com")
                .when().get("/api/users/email")
                .then()
                .statusCode(200)
                .body("voornaam", is("Budgethouder"))
                .body("familienaam", is("Budgethouder"))
                .body("email", is("budgethouder@test.com"))
                .body("rol", is("budgethouder"));
    }
    @Test
    @TestSecurity(authorizationEnabled = false)
    void shouldNotFindByInvalidEmail() {
        given()
                .queryParam("email", "admin@test.com")
                .when().get("/api/users/email")
                .then()
                .statusCode(404);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldFindByFamilienaam() {
        given()
                .queryParam("familienaam", "Budgethouder")
                .queryParam("voornaam", "Budgethouder")
                .when().get("/api/users/familienaamEnVoornaam")
                .then()
                .statusCode(200)
                .body("voornaam", is("Budgethouder"))
                .body("familienaam", is("Budgethouder"))
                .body("email", is("budgethouder@test.com"))
                .body("rol", is("budgethouder"));
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldNotFindByInvalidFamilienaam() {
        given()
                .queryParam("familienaam", "Budgethouder2")
                .queryParam("voornaam", "Budgethouder")
                .when().get("/api/users/familienaamEnVoornaam")
                .then()
                .statusCode(404);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldFindUserInfo() {
        given()
                .when().get("/api/users/me")
                .then()
                .statusCode(200)
                .body("voornaam", is("Budgethouder"))
                .body("familienaam", is("Budgethouder"))
                .body("email", is("budgethouder@test.com"))
                .body("rol", is("budgethouder"));
    }
}