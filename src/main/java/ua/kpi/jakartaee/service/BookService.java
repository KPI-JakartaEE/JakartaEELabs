package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDTO;

import java.util.List;

public interface BookService {
    boolean addBook(BookDTO bookDTO);
    // TODO(Yasnov): Create struct to hold more params, as this is not flexible and handy.
    // TODO(Yasnov): Something like BookSearchQuery.
    // TODO(Yasnov): Consider this change when real database is in place!
    List<BookDTO> getBooks(String author, String title, String keyword, String genre);
    boolean updateBook(BookDTO bookDTO);
    boolean deleteBookById(String bookId);
}
