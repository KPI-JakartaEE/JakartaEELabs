package ua.kpi.jakartaee.servlet;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDTO;
import ua.kpi.jakartaee.service.BookService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
@RolesAllowed("ADMIN")
public class AdminServlet extends Utf8HttpServlet {
    @Inject
    @Named("bookServiceImpl")
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bookService.addBook(BookDTO
                    .builder()
                    .bookId(req.getParameter("bookId"))
                    .title(req.getParameter("title"))
                    .author(req.getParameter("author"))
                    .genre(req.getParameter("genre"))
                    .keywords(List.of(req.getParameterValues("keywords")))
                    .description(req.getParameter("description"))
                    .build());
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bookService.updateBook(BookDTO
                    .builder()
                    .bookId(req.getParameter("bookId"))
                    .title(req.getParameter("title"))
                    .author(req.getParameter("author"))
                    .genre(req.getParameter("genre"))
                    .keywords(List.of(req.getParameterValues("keywords")))
                    .description(req.getParameter("description"))
                    .build());
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            bookService.deleteBookById(req.getParameter("bookId"));
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }
}
