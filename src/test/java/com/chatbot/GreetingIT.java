package com.chatbot;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import static io.restassured.RestAssured.given;

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
