package ua.kpi.jakartaee.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.BookSearchQuery;
import ua.kpi.jakartaee.service.BookService;

import java.io.IOException;
import java.util.List;

@WebServlet("/library/books")
public class LibraryServlet extends HttpServlet {

    @EJB(beanName = "bookServiceImpl")
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String keyword = request.getParameter("keyword");
        String genre = request.getParameter("genre");

        List<BookDto> books = bookService.getBooks(new BookSearchQuery(title, author, genre, keyword));

        request.setAttribute("books", books);
        request.getRequestDispatcher("/WEB-INF/view/catalog.jsp").forward(request, response);
    }
}
