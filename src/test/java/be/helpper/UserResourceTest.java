package be.helpper;


import be.helpper.users.User;
import be.helpper.users.UserResource;
import be.helpper.users.UserService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
class UserResourceTest {
    @Test
    @TestSecurity(user = "testAssistent@test.test", roles = "assistent")
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
    @TestSecurity(authorizationEnabled = true)
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