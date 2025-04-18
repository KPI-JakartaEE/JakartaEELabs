package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.BookSearchQuery;
import ua.kpi.jakartaee.exceptions.BookAlreadyExistsException;
import ua.kpi.jakartaee.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks();
    List<BookDto> getBooks(BookSearchQuery bookSearchQuery);
    List<BookDto> getBooksWithPagination(int pageNumber, int pageSize);
    List<BookDto> getBooksWithPaginationAndFiltration(BookSearchQuery bookSearchQuery, int pageNumber, int pageSize);
    void addBook(BookDto bookDto) throws BookAlreadyExistsException;
    void updateBook(BookDto bookDto) throws BookNotFoundException;
    void deleteBookById(String bookId) throws BookNotFoundException;
}
