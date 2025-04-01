package ua.kpi.jakartaee.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * This filter is applied to all requests and responses
 * Functionality:
 * - Sets UTF-8 encoding
 */
@WebFilter("/*")
public class AppFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        configureCharacterEncoding(request, response);
        chain.doFilter(request, response);
    }

    private void configureCharacterEncoding(ServletRequest request, ServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }
}