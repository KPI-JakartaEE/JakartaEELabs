package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.exceptions.BookServiceException;

import java.util.List;

public interface BookService {
    // TODO(Yasnov): Create struct to hold more params, as this is not flexible and handy.
    // TODO(Yasnov): Something like BookSearchQuery.
    // TODO(Yasnov): Consider this change when real database is in place!
    List<BookDto> getBooks(String author, String title, String keyword, String genre);
    List<BookDto> getBooks();
    void addBook(BookDto bookDto) throws BookServiceException;
    void updateBook(BookDto bookDto) throws BookServiceException;
    void deleteBookById(String bookId) throws BookServiceException;
}
