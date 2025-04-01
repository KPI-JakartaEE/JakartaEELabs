package ua.kpi.jakartaee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends Utf8HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getUserPrincipal() != null) {
            resp.sendRedirect("/ROOT/");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/security/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            req.login(username, password);
            resp.sendRedirect("/ROOT/admin");
        } catch (ServletException e) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("authErrorPresent", true);
            req.getRequestDispatcher("/WEB-INF/view/security/login.jsp").forward(req, resp);
        }
    }
}
