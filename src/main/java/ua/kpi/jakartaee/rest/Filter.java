package ua.kpi.jakartaee.rest;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import ua.kpi.jakartaee.utils.Jwt;

import java.util.stream.Stream;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class Filter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();

        if (isPublic(path)) {
            // Skip authentication for public endpoints
            return;
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !Jwt.validateToken(authorizationHeader)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    // Just in case we need more public endpoints, add all of them here
    private boolean isPublic(String path) {
        return Stream.of(
                "public", // /api/public/*
                "library",        // /api/library/*
                "books"           // /api/books/*
        ).anyMatch(path::startsWith);
    }
}
