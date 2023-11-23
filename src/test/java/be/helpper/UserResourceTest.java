package be.helpper;


import be.helpper.users.User;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static org.hamcrest.CoreMatchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@QuarkusTest
class UserResourceTest {
    private static final User testAssistent = new User("testAssistent", "testAssistent", "testAssistent@test.test", "testAssistent", "assistent");
    private static final User testBudgethouder = new User("testBudgethouder","testBudgethouder","testBudgethouder@test.test","testBudgethouder","budgethouder");

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
    public void freeForAll() {
        given()
                .when().get("/api/users/public")
                .then()
                .statusCode(200)
                .body(is("Welcom to Helpper!"));
    }
    @Test
    @TestSecurity(user = "testAssistent@test.test", roles = "assistent")
    public void userInfo() {
        given()
                .when().get("/api/users/me")
                .then()
                .statusCode(200)
                .body("email", is("testAssistent@test.test"))
                .body("voornaam", is("testAssistent"))
                .body("familienaam", is("testAssistent"))
                .body("rol", is("assistent"));
    }
    @Test
    @TestSecurity(authorizationEnabled = false)
//    @TestSecurity(user = "testAssistent@test.test", roles = "assistent")
    void shouldCreateANewUser() {
        User user = new User("testAssistent", "testAssistent", "testAssistent@test.test", "testAssistent", "assistent");
                given()
                        .body(user)
                        .contentType(ContentType.JSON)
                                .when()
                        .post("/api/users/nieuweUser").
                        then()
                        .statusCode(201)
                                .body("voornaam", is("testAssistent"),
                                        "email", is("testAssistent@test.test"));

    }
}