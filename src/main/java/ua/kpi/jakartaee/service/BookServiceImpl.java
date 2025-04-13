package ua.kpi.jakartaee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.repository.BookRepository;

import java.util.*;
import java.util.stream.Collectors;

@Stateless(name = "bookServiceImpl")
public class BookServiceImpl implements BookService {
    @EJB
    private BookRepository bookRepository;

    private List<String> filterKeywords(List<String> keywords) {
        return keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isBlank())
                .collect(Collectors.toList());
    }

    @Override
    public void addBook(BookDto bookDto) {
        // Chance of collision should be zero to none...
        bookDto.setBookId(UUID.randomUUID().toString());
        bookDto.setKeywords(filterKeywords(bookDto.getKeywords()));
        bookRepository.save(new Book(bookDto));
    }

    @Override
    public List<BookDto> getBooks() {
        List<BookDto> books = new ArrayList<>();
        // TODO: Page number and size should be received from UI.
        for (Book book : bookRepository.getBooksWithPagination(1024, 1024))
        {
//            books.add(book.toBookDto());
        }
        return books;
    }

    @Override
    public List<BookDto> getBooks(String author, String title, String keyword, String genre) {
        List<BookDto> books = new ArrayList<>();
        // TODO: Kinda broken...
        for (Book book : bookRepository.findBooksFilteredByFields(author, title, keyword, genre))
        {
//            books.add(book.toBookDto());
        }
        return books;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        bookDto.setKeywords(filterKeywords(bookDto.getKeywords()));
        bookRepository.update(new Book(bookDto));
    }

    @Override
    public void deleteBookById(String bookId) {
        bookRepository.deleteById(UUID.fromString(bookId));
    }
}
