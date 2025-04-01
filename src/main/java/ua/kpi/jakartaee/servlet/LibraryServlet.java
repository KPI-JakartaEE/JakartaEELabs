package ua.kpi.jakartaee.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.jakartaee.dto.BookDTO;
import ua.kpi.jakartaee.service.BookService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/library/books")
public class LibraryServlet extends HttpServlet {
    private BookService bookService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String keyword = request.getParameter("keyword");
        String genre = request.getParameter("genre");

        List<BookDTO> books = bookService.getBooks();

        if (author != null && !author.isEmpty()) {
            books = books.stream().filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase())).collect(Collectors.toList());
        }
        if (title != null && !title.isEmpty()) {
            books = books.stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
        }
        if (keyword != null && !keyword.isEmpty()) {
            books = books.stream().filter(book -> book.getKeywords().stream().anyMatch(k -> k.toLowerCase().contains(keyword.toLowerCase()))).collect(Collectors.toList());
        }
        if (genre != null && !genre.isEmpty()) {
            books = books.stream().filter(book -> book.getGenre().toLowerCase().contains(genre.toLowerCase())).collect(Collectors.toList());
        }

        request.setAttribute("books", books);
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }
}
