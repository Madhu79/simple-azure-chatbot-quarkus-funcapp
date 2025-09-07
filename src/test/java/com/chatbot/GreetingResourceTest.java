package com.chatbot;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testGetHelloEndpoint() {
        given()
          .when().get("/messages/hello")
          .then().statusCode(200);
    }

    @Test
    public void testPostMessageEndpoint() {
        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"message\": \"Hello from a test!\"}")
        .when()
          .post("/messages/message")
        .then()
          .statusCode(200);
    }

    @Test
    public void testPutMessageEndpoint() {
        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"message\": \"Updated content\"}")
        .when()
          .put("/messages/123")
        .then()
          .statusCode(200);
    }

    @Test
    public void testPatchMessageEndpoint() {
        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"status\": \"read\"}")
        .when()
          .patch("/messages/456")
        .then()
          .statusCode(200);
    }
}