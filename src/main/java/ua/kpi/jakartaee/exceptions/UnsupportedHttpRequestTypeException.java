package ua.kpi.jakartaee.exceptions;

public class UnsupportedHttpRequestTypeException extends RuntimeException {
    public UnsupportedHttpRequestTypeException(String message) {
        super(message);
    }
}
