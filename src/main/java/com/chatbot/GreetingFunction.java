package com.chatbot;

import io.quarkus.funqy.Funq;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

public class GreetingFunction {

    private static final Logger LOG = Logger.getLogger(GreetingFunction.class);

    @Funq("hello")
    public String getHello() {
        LOG.info("Received a GET request for a greeting.");
        return "Hello from the Quarkus chatbot! I am alive.";
    }

    @Funq("hello") 
    public Response handlePost(JsonObject jsonBody) {
        LOG.info("Quarkus HTTP trigger function processed a request.");

        String userMessage = "";
        if (jsonBody.containsKey("message")) {
            userMessage = ((JsonString) jsonBody.get("message")).getString();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Please pass a JSON object with a 'message' key in the request body\"}")
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();
        }

        // This is the placeholder response. In a later step, you'll replace this with the AI agent call.
        String botResponse = String.format("I received your message via Quarkus: '%s'. Thank you!", userMessage);

        // Return the response as JSON
        return Response.ok()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .entity("{\"response\": \"" + botResponse + "\"}")
                .build();
    }

    // PUT /api/messages/{id}
    // This method is for updating an entire message.
    // The Funqy framework automatically maps {id} from the path to the String id parameter.
    
    @Funq("messages/{id}")
     public Response putMessage(String id, JsonObject jsonBody) {
        LOG.infof("Received a PUT request to update message ID: %s", id);

        // In a real application, you would use 'id' to find a message in a database
        // and update its content with the new 'jsonBody'.
        String newMessage = "";
        if (jsonBody.containsKey("message")) {
            newMessage = ((JsonString) jsonBody.get("message")).getString();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Please pass a JSON object with a 'message' key in the request body\"}")
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();
        }

        String botResponse = String.format("I received a PUT request to update message '%s' with content: '%s'", id, newMessage);

        return Response.ok()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .entity("{\"response\": \"" + botResponse + "\"}")
                .build();
    }

    // PATCH /api/messages/{id}
    // This method is for partially updating a message.
    @Funq("messages/{id}")
    public Response patchMessage(String id, JsonObject jsonBody) {
        LOG.infof("Received a PATCH request to partially update message ID: %s", id);
        
        // In a real application, you would apply the changes from 'jsonBody'
        // to a message found by its 'id'.
        String newStatus = "";
        if (jsonBody.containsKey("status")) {
            newStatus = ((JsonString) jsonBody.get("status")).getString();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Please pass a JSON object with a 'status' key in the request body\"}")
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .build();
        }

        String botResponse = String.format("I received a PATCH request to change status of message '%s' to: '%s'", id, newStatus);
        
        return Response.ok()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .entity("{\"response\": \"" + botResponse + "\"}")
                .build();
    }
}
