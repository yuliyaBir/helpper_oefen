package be.helpper.goedkeuringen;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class GoedkeuringResourceTest {

    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    @TestTransaction
    void shouldCreateGoedkeuring() {
        given()
                .body("{\"commentaar\":\"test\", \"uren\":3}")
                .contentType(ContentType.JSON)
                .when().post("/goedkeuringen/nieuwVoorPrestatie/2")
                .then()
                .statusCode(200);
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    @TestTransaction
    void shouldNotCreateSecondGoedkeuring() {
        given()
                .body("{\"commentaar\":\"test\", \"uren\":3}")
                .contentType(ContentType.JSON)
                .when().post("/goedkeuringen/nieuwVoorPrestatie/1")
                .then()
                .statusCode(409);
    }
    @Test
    @TestSecurity(user = "assistent@test.com", roles = "assistent")
    @TestTransaction
    void shouldNotCreateGoedkeuringWithInvalidRole() {
        given()
                .body("{\"commentaar\":\"test\", \"uren\":3}")
                .contentType(ContentType.JSON)
                .when().post("/goedkeuringen/nieuwVoorPrestatie/2")
                .then()
                .statusCode(403);
    }
}