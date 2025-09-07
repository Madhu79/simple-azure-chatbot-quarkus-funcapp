package com.chatbot;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import jakarta.ws.rs.core.MediaType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingTest {

    @Test
    public void testPostHelloEndpoint() {
        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"message\": \"Hello from a test!\"}")
        .when()
          .post("/api/hello")
        .then()
          .statusCode(200);
    }

    // Test a GET request for a simple greeting
    @Test
    public void testGetHelloEndpoint() {
        given()
          .when()
            .get("/api/hello")
          .then()
            .statusCode(200);
    }

    // Test a PUT request to update an entire message
    @Test
    public void testPutMessageEndpoint() {
        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"message\": \"This is an updated message.\", \"id\": \"123\"}")
        .when()
          .put("/api/messages/123")
        .then()
          .statusCode(200);
    }
    
    // Test a PATCH request to partially update a message
    @Test
    public void testPatchMessageEndpoint() {
        given()
          .contentType(MediaType.APPLICATION_JSON)
          .body("{\"status\": \"read\"}")
        .when()
          .patch("/api/messages/456")
        .then()
          .statusCode(200);
    }

}
