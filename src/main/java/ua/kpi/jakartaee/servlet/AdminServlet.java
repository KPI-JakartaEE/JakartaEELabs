package ua.kpi.jakartaee.servlet;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.HttpRequestType;
import ua.kpi.jakartaee.service.BookService;
import ua.kpi.jakartaee.service.HttpRequestProcessor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@WebServlet("/admin")
@RolesAllowed("ADMIN")
public class AdminServlet extends HttpServlet {
//    @Inject
//    @Named("bookServiceImpl")
    @EJB(beanName = "bookServiceImpl")
    private BookService bookService;

//    @Inject
//    @Named("adminRequestProcessor")
    @EJB(beanName = "adminRequestProcessor")
    private HttpRequestProcessor<BookDto> httpRequestProcessor;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookService.getBooks());
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpRequestType httpRequestType = HttpRequestType.valueOf(req.getParameter("_method").toUpperCase());
            httpRequestProcessor.process(req, resp, httpRequestType, BookDto
                    .builder()
                    .bookId(req.getParameter("bookId"))
                    .title(req.getParameter("title"))
                    .author(req.getParameter("author"))
                    .genre(req.getParameter("genre"))
                    .keywords(req.getParameterValues("keywords") == null ? Collections.emptyList() : Arrays.asList(req.getParameterValues("keywords")))
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
