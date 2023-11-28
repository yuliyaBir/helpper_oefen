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
                .statusCode(201)
                .body(
                        "email", is("test@test.com"),
                        "voornaam", is("test"));
    }
}