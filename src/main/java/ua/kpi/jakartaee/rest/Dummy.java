package ua.kpi.jakartaee.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// Example endpoints.
// Read comments :)
@Path("")
public class Dummy {

    // This one is private, we need to authenticate via api/public/auth (see Authentication class)
    // HTTP request looks something like this:
    // POST http://localhost:8080/api/dummy
    // Authorization: <token received from /api/public/auth>
    // Content-Type: application/json
    //
    // {
    //   "data": "dummy"
    // }
    @POST
    @Path("/dummy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dummy(String jsonData) {
        return Response.ok("{\"message\": \"You are authorized!\", \"data\": " + jsonData + "}").build();
    }

    // This one is public. Request would look like the one above, but without Authorization header.
    @POST
    @Path("/public/dummy")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publicDummy(String jsonData) {
        return Response.ok("{\"message\": \"No auth required.\", \"data\": " + jsonData + "}").build();
    }
}
