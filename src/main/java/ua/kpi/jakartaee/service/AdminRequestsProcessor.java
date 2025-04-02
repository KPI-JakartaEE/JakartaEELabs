package ua.kpi.jakartaee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDTO;

@ApplicationScoped
public class AdminRequestsProcessor implements HttpRequestProcessor<BookDTO> {

    @Inject
    @Named("bookServiceImpl")
    private BookService bookService;

    @Inject
    private EntityValidator entityValidator;

    @Override
    public void onPost(HttpServletRequest req, HttpServletResponse resp, BookDTO data) {
        entityValidator.validate(data);
        bookService.addBook(data);
    }

    @Override
    public void onPut(HttpServletRequest req, HttpServletResponse resp, BookDTO data) {
        entityValidator.validate(data);
        bookService.updateBook(data);
    }

    @Override
    public void onDelete(HttpServletRequest req, HttpServletResponse resp, BookDTO data) {
        bookService.deleteBookById(req.getParameter("bookId"));
    }
}
