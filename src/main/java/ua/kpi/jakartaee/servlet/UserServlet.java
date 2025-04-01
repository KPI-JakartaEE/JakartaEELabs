package ua.kpi.jakartaee.servlet;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.exceptions.PageNotFoundException;
import ua.kpi.jakartaee.exceptions.UserNotFoundException;
import ua.kpi.jakartaee.repository.model.User;
import ua.kpi.jakartaee.service.UserService;

import java.io.IOException;

@WebServlet("/team/members/*")
public class UserServlet extends Utf8HttpServlet {
    @Inject
    @Named("userServiceImpl")
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pathInfo = req.getPathInfo();

            User user = userService.getUser(pathInfo);
            req.setAttribute("user", user);

            String pageName = userService.getUserPage(pathInfo);

            req.getRequestDispatcher(pageName).forward(req, resp);
        } catch (UserNotFoundException | PageNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
