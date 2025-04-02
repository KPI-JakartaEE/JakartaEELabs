package ua.kpi.jakartaee.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.HttpRequestType;
import ua.kpi.jakartaee.exceptions.UnsupportedHttpRequestTypeException;

public interface HttpRequestProcessor<T> {
    default void process(HttpServletRequest req, HttpServletResponse resp, HttpRequestType httpRequestType, T data) throws Exception {
        switch (httpRequestType) {
            case POST -> onPost(req, resp, data);
            case PUT -> onPut(req, resp, data);
            case DELETE -> onDelete(req, resp, data);
            default -> throw new UnsupportedHttpRequestTypeException("Unsupported request type: " + httpRequestType);
        }
    }
    void onPost(HttpServletRequest req, HttpServletResponse resp, T data) throws Exception;
    void onPut(HttpServletRequest req, HttpServletResponse resp, T data) throws Exception;
    void onDelete(HttpServletRequest req, HttpServletResponse resp, T data) throws Exception;
}
