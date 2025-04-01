package ua.kpi.jakartaee.servlet;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.repository.model.User;
import ua.kpi.jakartaee.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class ApplicationServlet extends HttpServlet {
    @Inject
    @Named("userServiceImpl")
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}
