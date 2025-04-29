package ua.kpi.jakartaee.rest;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import ua.kpi.jakartaee.dto.HttpRequestType;
import ua.kpi.jakartaee.utils.Jwt;

import java.util.stream.Stream;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class Filter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();

        if (isPublic(path, method)) {
            // Skip authentication for public endpoints/methods
            return;
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !Jwt.validateToken(authorizationHeader)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    //  Just in case we need more public endpoints depending on the HTTP method type
    private boolean isPublic(String path, String method) {
        HttpRequestType httpRequestType = HttpRequestType.valueOf(method.toUpperCase());
        boolean isPublic = switch (httpRequestType) {
            case GET -> processGetEndpoints(path);

            /*
                In case you need public endpoints for other http methods, you need to implement such methods:
                - boolean processPostEndpoints(String path)
                - boolean processPutEndpoints(String path)
                - boolean processPatchEndpoints(String path)
                - boolean processDeleteEndpoints(String path)
                Similar to processGetEndpoints(String path)
             */
            case POST, PUT, PATCH, DELETE -> false;
        };

        if (!isPublic) {
            isPublic = processPublicEndpointsForAllHttpMethods(path);
        }
        return isPublic;
    };

    // These endpoints will be public only for GET methods in HTTP
    private boolean processGetEndpoints(String path) {
        return Stream.of(
                "library",        // /api/library/*
                "books"                   // /api/books/*
        ).anyMatch(path::startsWith);
    }


    /*
        These endpoints will be public for all http methods (GET, POST, PUT, PATCH, DELETE) so that
        you won't need to create duplicates of endpoints for all methods
     */
    private boolean processPublicEndpointsForAllHttpMethods(String path) {
        return Stream.of(
                "public"     // /api/public/*
        ).anyMatch(path::startsWith);
    }
}
