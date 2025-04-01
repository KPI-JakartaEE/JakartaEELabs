package ua.kpi.jakartaee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDTO;
import ua.kpi.jakartaee.dto.RequestData;

@ApplicationScoped
@Named("adminRequestProcessor")
public class AdminRequestsProcessor implements HttpRequestProcessor {

    @Inject
    @Named("bookServiceImpl")
    private BookService bookService;


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, RequestData data) {}

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp, RequestData data) {
        BookDTO bookDTO = (BookDTO) data;
        bookService.addBook(bookDTO);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp, RequestData data) {
        BookDTO bookDTO = (BookDTO) data;
        bookService.updateBook(bookDTO);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp, RequestData data) {
        bookService.deleteBookById(req.getParameter("bookId"));
    }
}
