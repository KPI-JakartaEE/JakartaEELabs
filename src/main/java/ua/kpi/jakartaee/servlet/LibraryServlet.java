package ua.kpi.jakartaee.servlet;

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

@WebServlet("/library/books")
public class LibraryServlet extends Utf8HttpServlet {
    @Inject
    @Named("bookServiceImpl")
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String keyword = request.getParameter("keyword");
        String genre = request.getParameter("genre");

        List<BookDTO> books = bookService.getBooks(author, title, keyword, genre);

        request.setAttribute("books", books);
        request.getRequestDispatcher("/WEB-INF/view/catalog.jsp").forward(request, response);
    }
}
