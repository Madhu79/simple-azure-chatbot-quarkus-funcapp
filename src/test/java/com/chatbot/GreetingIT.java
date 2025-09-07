package com.chatbot;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
public class GreetingIT {

    @Test
    public void testGetHelloEndpoint() {
        given()
          .when()
            .get("/api/hello")
          .then()
            .statusCode(200);
    }
    

}
