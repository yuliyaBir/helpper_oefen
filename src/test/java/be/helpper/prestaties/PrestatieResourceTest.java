package be.helpper.prestaties;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PrestatieResourceTest {
    @Test
    @Order(1)
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldFindLijstVanPrestatiesZonderGoedkeuringVoorBepaaldeBudgethouder() {
        given()
                .pathParams("budgethouderId", 1)
                .contentType(ContentType.JSON)
                .when().get("/prestaties/budgethouder/{budgethouderId}/zonderGoedkeuring")
                .then()
                .statusCode(200)
                .body("$", hasSize(1));
    }
    @Test
    @TestSecurity(user = "assistent@test.com", roles = "assistent")
    @Order(2)
    @TestTransaction
    void shouldCreatePrestatie() {
        given()
                .body("{\"naam\":\"test\", \"omschrijving\":\"test\",\"assistentId\":2, \"budgethouderFamilienaam\":\"budgethouder\", \"budgethouderVoornaam\":\"budgethouder\"}")
                .contentType(ContentType.JSON)
                .when().post("/prestaties/nieuw")
                .then()
                .statusCode(200);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    @TestTransaction
    void shouldNotCreatePrestatieWithInvalidRole() {
        given()
                .body("{\"naam\":\"test\", \"omschrijving\":\"test\",\"assistentId\":2, \"budgethouderFamilienaam\":\"budgethouder\", \"budgethouderVoornaam\":\"budgethouder\"}")
                .contentType(ContentType.JSON)
                .when().post("/prestaties/nieuw")
                .then()
                .statusCode(403);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    @Order(3)
    @TestTransaction
    void shouldDeletePrestatieById() {
        given()
                .pathParams("id", 10)
                .contentType(ContentType.JSON)
                .when().get("/prestaties/{id}")
                .then()
                .statusCode(200)
                .body("naam", is("test"))
                .body("omschrijving", is("test"));
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    @TestTransaction
    void shouldFindPrestatieById() {
        given()
                .pathParams("id", 1)
                .contentType(ContentType.JSON)
                .when().get("/prestaties/{id}")
                .then()
                .statusCode(200)
                .body("naam", is("test1"))
                .body("omschrijving", is("test1"));
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    @TestTransaction
    void shouldNotFindPrestatieByInvalidId() {
        given()
                .pathParams("id", Long.MAX_VALUE)
                .contentType(ContentType.JSON)
                .when().get("/prestaties/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "")
    @TestTransaction
    void shouldNotFindPrestatieBydIdWithInvalidRole() {
        given()
                .pathParams("id", 1)
                .contentType(ContentType.JSON)
                .when().get("/prestaties/{id}")
                .then()
                .statusCode(403);
    }
}