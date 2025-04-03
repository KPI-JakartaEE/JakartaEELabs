package ua.kpi.jakartaee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.exceptions.BookServiceException;

@Stateless(name = "adminRequestProcessor")
public class AdminRequestsProcessor implements HttpRequestProcessor<BookDto> {

    @EJB(beanName = "bookServiceImpl")
    private BookService bookService;

    @EJB
    private EntityValidator entityValidator;

    @Override
    public void onPost(HttpServletRequest req, HttpServletResponse resp, BookDto data) throws BookServiceException {
        entityValidator.validate(data);
        bookService.addBook(data);
    }

    @Override
    public void onPut(HttpServletRequest req, HttpServletResponse resp, BookDto data) throws BookServiceException {
        entityValidator.validate(data);
        bookService.updateBook(data);
    }

    @Override
    public void onDelete(HttpServletRequest req, HttpServletResponse resp, BookDto data) throws BookServiceException {
        bookService.deleteBookById(req.getParameter("bookId"));
    }
}
