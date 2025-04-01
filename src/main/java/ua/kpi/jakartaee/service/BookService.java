package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDTO;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO);
    List<BookDTO> getBooks();
    void updateBook(BookDTO bookDTO);
    void deleteBookById(String bookId);
}
