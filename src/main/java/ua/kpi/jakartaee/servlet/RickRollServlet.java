package ua.kpi.jakartaee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Why shouldn't I do this after all...
 */
@WebServlet("/rickroll")
public class RickRollServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String ATTRIBUTE = "rickroll";
        final String VALUE = "https://www.youtube.com/embed/dQw4w9WgXcQ";
        req.setAttribute(ATTRIBUTE, VALUE);

        final String pageName = "/WEB-INF/view/rickroll.jsp";
        req.getRequestDispatcher(pageName).forward(req, resp);
    }
}
