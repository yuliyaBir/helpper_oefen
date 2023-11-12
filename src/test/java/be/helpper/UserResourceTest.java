package be.helpper;

import be.helpper.users.User;
import be.helpper.users.UserService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {
    @InjectMock
    UserService userService;
//    @Test
//    public void shouldFindUserById() {
//        Mockito.when(userService.findById(Long.valueOf(1)))
//                .thenReturn(new User("test", "test", "test.test@gmail.com", "test"));
//        given()
//                .when().get("/hello")
//                .then()
//                .statusCode(200)
//                .body(is("Hello RESTEasy"));
//    }

}