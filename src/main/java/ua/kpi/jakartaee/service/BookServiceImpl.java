package ua.kpi.jakartaee.service;

import jakarta.ejb.*;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.BookSearchQuery;
import ua.kpi.jakartaee.entity.Author;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.exceptions.BookAlreadyExistsException;
import ua.kpi.jakartaee.exceptions.BookNotFoundException;
import ua.kpi.jakartaee.repository.AuthorRepository;
import ua.kpi.jakartaee.repository.BookRepository;

import java.util.*;

@Stateless(name = "bookServiceImpl")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookServiceImpl implements BookService {
    @EJB
    private BookRepository bookRepository;

    @EJB
    private AuthorRepository authorRepository;

    @EJB
    private BookConvertor bookConvertor;

    private String trimValue(String value) {
        return value == null ? null : value.trim();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBook(BookDto bookDto) throws BookAlreadyExistsException {
        if (bookRepository.existsByTitleAndAuthorName(trimValue(bookDto.getTitle()), trimValue(bookDto.getAuthor()))) {
            throw new BookAlreadyExistsException("bookDto is already exist");
        }
        bookRepository.save(bookConvertor.toEntity(bookDto));
    }

    @Override
    public List<BookDto> getBooks() {
        List<BookDto> books = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            books.add(bookConvertor.toDto(book));
        }
        return books;
    }

    @Override
    public List<BookDto> getBooks(BookSearchQuery bookSearchQuery) {
        List<Book> booksFromDB = bookRepository.findBooksFilteredByFields(
                trimValue(bookSearchQuery.getTitle()),
                trimValue(bookSearchQuery.getAuthor()),
                trimValue(bookSearchQuery.getGenre()),
                trimValue(bookSearchQuery.getKeyword())
        );
        List<BookDto> books = new ArrayList<>();
        for (Book book : booksFromDB) {
            books.add(bookConvertor.toDto(book));
        }
        return books;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBook(BookDto bookDto) {
        bookRepository.saveOrUpdate(bookConvertor.toEntity(bookDto));

        // This is for transaction testing
        if (bookDto.getTitle().equals("Test")) {
            throw new RuntimeException("All changes in DB should rollback because book title is 'Test'");
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteBookById(String bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(UUID.fromString(bookId)).orElseThrow(
                () -> new BookNotFoundException("Book not found")
        );
        Author author = book.getAuthor();

        bookRepository.deleteById(UUID.fromString(bookId));

        if (bookRepository.countByAuthorId(author.getId()) == 0) {
            authorRepository.deleteById(author.getId());
        }

    }
}
