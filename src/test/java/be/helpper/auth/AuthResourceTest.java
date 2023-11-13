package be.helpper.auth;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static io.restassured.RestAssured.given;
import static java.util.function.Predicate.not;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class AuthResourceTest {

    @Test
    void loginValidCredentials() {
        given()
                .body("{\"name\":\"admin\",\"password\":\"quarkus\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("&",hasToString(""));
    }
    @Test
    void loginInvalidCredentials() {
        given()
                .body("{\"name\":\"admin\",\"password\":\"not-quarkus\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/auth/login")
                .then()
                .statusCode(401);
    }
}