package be.helpper;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.hamcrest.CoreMatchers.is;


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
                .body("voornaam", is("budgethouder"))
                .body("familienaam", is("budgethouder"))
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
                .body("voornaam", is("budgethouder"))
                .body("familienaam", is("budgethouder"))
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
                .queryParam("familienaam", "budgethouder")
                .queryParam("voornaam", "budgethouder")
                .when().get("/api/users/familienaamEnVoornaam")
                .then()
                .statusCode(200)
                .body("voornaam", is("budgethouder"))
                .body("familienaam", is("budgethouder"))
                .body("email", is("budgethouder@test.com"))
                .body("rol", is("budgethouder"));
    }
    @Test
    @TestSecurity(user = "budgethouder@test.com", roles = "budgethouder")
    void shouldNotFindByInvalidFamilienaam() {
        given()
                .queryParam("familienaam", "budgethouder2")
                .queryParam("voornaam", "budgethouder")
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
                .body("voornaam", is("budgethouder"))
                .body("familienaam", is("budgethouder"))
                .body("email", is("budgethouder@test.com"))
                .body("rol", is("budgethouder"));
    }
}