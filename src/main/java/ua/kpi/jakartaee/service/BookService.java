package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDTO;

import java.util.List;

public interface BookService {
    public List<BookDTO> getBooks();
}