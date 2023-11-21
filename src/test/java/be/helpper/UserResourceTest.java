package be.helpper;


import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {
    @Test
    public void freeForAll() {
        given()
                .when().get("/api/users/public")
                .then()
                .statusCode(200)
                .body(is("Welcom to Helpper!"));
    }
    @Test
    @TestSecurity(user = "mido@gh.com", roles = "budgethouder")
    public void userInfo() {
        given()
                .when().get("/api/users/me")
                .then()
                .statusCode(200)
                .body("email", is("mido@gh.com"))
                .body("voornaam", is("hryry"))
                .body("familienaam", is("Mikao"))
                .body("rol", is("budgethouder"));
    }
}