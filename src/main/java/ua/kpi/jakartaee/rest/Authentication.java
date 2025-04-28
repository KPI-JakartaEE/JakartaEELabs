package ua.kpi.jakartaee.rest;

import ua.kpi.jakartaee.utils.Jwt;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/public/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Authentication {

    // TODO: This should probably be stored elsewhere...
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    public static class LoginRequest {
        public String username;
        public String password;
    }

    // Returns JSON object with token
    @POST
    public Response login(LoginRequest request) {
        if (LOGIN.equals(request.username) && PASSWORD.equals(request.password)) {
            String token = Jwt.generateToken(request.username);
            Map<String, String> resp = new HashMap<>();
            resp.put("token", token);
            return Response.ok(resp).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
