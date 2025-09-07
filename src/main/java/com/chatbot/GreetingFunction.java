package com.chatbot;

import io.quarkus.funqy.Funq;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

public class GreetingFunction {

    private static final Logger LOG = Logger.getLogger(GreetingFunction.class);

    @Funq
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
}