package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.BookSearchQuery;
import ua.kpi.jakartaee.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks(BookSearchQuery bookSearchQuery);
    List<BookDto> getBooks();
    void addBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    void deleteBookById(String bookId) throws BookNotFoundException;
}
