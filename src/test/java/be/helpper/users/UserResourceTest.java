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
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
class UserResourceTest {
    private static User user = new User("test", "test","test@test.com", "test", "assistent");

       //    @Test
//    @TestSecurity(user = "twister@twister.com", roles = "assistent")
//    void getCurrentUserGelukt() {
//        given()
//                .when().post("/api/users/me")
//                .then()
//                .statusCode(200)
//                .body(not(null));
//    }
//@Test
//@TestSecurity(authorizationEnabled = false)
//void create() {
//    given()
//            .body("{\"voornaam\":\"test\",\"familienaam\":\"test\",\"email\":\"test2@test.com\", \"wachtwoord\":\"test2\", \"rol\":\"assistent\"}")
//            .contentType(ContentType.JSON)
//            .when().post("/api/v1/users")
//            .then()
//            .statusCode(201);
//}
    @Test
    @TestSecurity(authorizationEnabled = false)
    void shouldNotGetUnknownUser() {
        Long randomId = new Random().nextLong();
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .pathParam("id", randomId).
                when()
                .get("/api/users/{id}").
                then()
                .statusCode(NOT_FOUND.getStatusCode());
    }
//    @Test
//    @TestSecurity(authorizationEnabled = false)
//    void shouldCreateANewUser() {
//        User user = new User("testing", "testing", "testing@testing.com", "testing", "assistent");
//
//        String location =
//                given()
//                        .body(user)
//                        .header(CONTENT_TYPE, APPLICATION_JSON)
//                        .header(ACCEPT, APPLICATION_JSON).
//                        when()
//                        .post("/api/artists").
//                        then()
//                        .statusCode(201)
//                        .extract().header("Location");
//
//        // Extracts the Location and stores the id
//        assertTrue(location.contains("/api/users"));
//        String[] segments = location.split("/");
//        "" + user.getId() = segments[segments.length - 1];
//        assertNotNull(user.getId());
//    }
//    @Test
//    @TestSecurity(authorizationEnabled = false)
//    void deleteUserGelukt(){
//        given()
//            .body("{\"voornaam\":\"test\",\"familienaam\":\"test\",\"email\":\"test2@test.com\", \"wachtwoord\":\"test2\", \"rol\":\"assistent\"}")
//            .contentType(ContentType.JSON)
//            .when().post("/api/v1/users")
//            .then()
//            .statusCode(204);
//    }

}