package ua.kpi.jakartaee.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.HttpRequestType;
import ua.kpi.jakartaee.dto.RequestData;

public interface HttpRequestProcessor {
    default void process(HttpServletRequest req, HttpServletResponse resp, String requestType, RequestData data) {
        HttpRequestType httpRequestType = HttpRequestType.valueOf(requestType.toUpperCase());
        switch (httpRequestType) {
            case GET -> doGet(req, resp, data);
            case POST -> doPost(req, resp, data);
            case PUT -> doPut(req, resp, data);
            case DELETE -> doDelete(req, resp, data);
        }
    }
    void doGet(HttpServletRequest req, HttpServletResponse resp, RequestData data);
    void doPost(HttpServletRequest req, HttpServletResponse resp, RequestData data);
    void doPut(HttpServletRequest req, HttpServletResponse resp, RequestData data);
    void doDelete(HttpServletRequest req, HttpServletResponse resp, RequestData data);
}
