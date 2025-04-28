package ua.kpi.jakartaee.rest;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import ua.kpi.jakartaee.utils.Jwt;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class Filter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();

        if (path.startsWith("public")) {
            // Skip authentication for public endpoints
            return;
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !Jwt.validateToken(authorizationHeader)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
