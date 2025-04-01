package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDTO;

public interface BookService {
    void addBook(BookDTO bookDTO);
    void updateBook(BookDTO bookDTO);
    void deleteBookById(String bookId);
}
