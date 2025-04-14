package ua.kpi.jakartaee.service;

import jakarta.ejb.*;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.repository.BookRepository;

import java.util.*;
import java.util.stream.Collectors;

@Stateless(name = "bookServiceImpl")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookServiceImpl implements BookService {
    @EJB
    private BookRepository bookRepository;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBook(BookDto bookDto) {
        bookRepository.save(BookConvertor.toBook(bookDto));
    }

    @Override
    public List<BookDto> getBooks() {
        List<BookDto> books = new ArrayList<>();
        for (Book book : bookRepository.findAll())
        {
            books.add(BookConvertor.toBookDto(book));
        }
        return books;
    }

    @Override
    public List<BookDto> getBooks(String author, String title, String keyword, String genre) {
        List<BookDto> books = new ArrayList<>();
        for (Book book : bookRepository.findBooksFilteredByFields(author, title, keyword, genre))
        {
            books.add(BookConvertor.toBookDto(book));
        }
        return books;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBook(BookDto bookDto) {
        bookDto.setKeywords(BookConvertor.filterKeywords(bookDto.getKeywords()));
        bookRepository.update(new Book(bookDto));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBookById(String bookId) {
        bookRepository.deleteById(UUID.fromString(bookId));
    }
}
