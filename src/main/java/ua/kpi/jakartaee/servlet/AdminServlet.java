package ua.kpi.jakartaee.servlet;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDTO;
import ua.kpi.jakartaee.service.BookService;
import ua.kpi.jakartaee.service.HttpRequestProcessor;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/admin")
@RolesAllowed("ADMIN")
public class AdminServlet extends HttpServlet {
    @Inject
    @Named("bookServiceImpl")
    private BookService bookService;

    @Inject
    @Named("adminRequestProcessor")
    private HttpRequestProcessor httpRequestProcessor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookService.getBooks());
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            httpRequestProcessor.process(req, resp, req.getParameter("_method"), BookDTO
                    .builder()
                    .bookId(req.getParameter("bookId"))
                    .title(req.getParameter("title"))
                    .author(req.getParameter("author"))
                    .genre(req.getParameter("genre"))
                    .keywords(Arrays.asList(req.getParameterValues("keywords")))
                    .description(req.getParameter("description"))
                    .build());
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        req.setAttribute("books", bookService.getBooks());
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }
}
