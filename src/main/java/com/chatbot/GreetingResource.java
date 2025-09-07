package com.chatbot;

import org.jboss.logging.Logger;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class GreetingResource {

    private static final Logger LOG = Logger.getLogger(GreetingResource.class);

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        LOG.info("Received a GET request for a greeting.");
        return "Hello from the Quarkus chatbot! I am alive.";
    }

    @POST
    @Path("/message")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postMessage(JsonObject jsonBody) {
        LOG.info("Received a POST request with a message.");

        if (!jsonBody.containsKey("message")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Missing 'message' key in request body\"}")
                    .build();
        }

        String userMessage = jsonBody.getString("message");
        String botResponse = String.format("I received your message via JAX-RS: '%s'. Thank you!", userMessage);

        return Response.ok("{\"response\": \"" + botResponse + "\"}").build();
    }

    @PUT
    @Path("/messages/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putMessage(@PathParam("id") String id, JsonObject jsonBody) {
        LOG.infof("Received a PUT request to update message ID: %s", id);

        if (!jsonBody.containsKey("message")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Missing 'message' key in request body\"}")
                    .build();
        }

        String newMessage = jsonBody.getString("message");
        String botResponse = String.format("Updated message '%s' with content: '%s'", id, newMessage);

        return Response.ok("{\"response\": \"" + botResponse + "\"}").build();
    }

    @PATCH
    @Path("/messages/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response patchMessage(@PathParam("id") String id, JsonObject jsonBody) {
        LOG.infof("Received a PATCH request to partially update message ID: %s", id);

        if (!jsonBody.containsKey("status")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Missing 'status' key in request body\"}")
                    .build();
        }

        String newStatus = jsonBody.getString("status");
        String botResponse = String.format("Changed status of message '%s' to: '%s'", id, newStatus);

        return Response.ok("{\"response\": \"" + botResponse + "\"}").build();
    }
}