package ua.kpi.jakartaee.service;

import jakarta.ejb.*;
import org.apache.commons.lang3.StringUtils;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.BookSearchQuery;
import ua.kpi.jakartaee.entity.Author;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.entity.Keyword;
import ua.kpi.jakartaee.exceptions.BookAlreadyExistsException;
import ua.kpi.jakartaee.exceptions.BookNotFoundException;
import ua.kpi.jakartaee.repository.AuthorRepository;
import ua.kpi.jakartaee.repository.BookRepository;

import java.util.*;
import java.util.function.Consumer;

@Stateless(name = "bookServiceImpl")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookServiceImpl implements BookService {
    @EJB
    private BookRepository bookRepository;

    @EJB
    private AuthorRepository authorRepository;

    @EJB
    private BookConvertor bookConvertor;

    @EJB
    private KeywordService keywordService;

    private boolean checkIfFieldShouldBeModified(String oldValue, String newValue, boolean includeBlankValuesCheck) {
        if (includeBlankValuesCheck) {
            return newValue != null && !newValue.isBlank() && !newValue.equals(oldValue);
        }
        return newValue != null && !newValue.equals(oldValue);
    }

    private boolean checkIfFieldShouldBeModified(String oldValue, String newValue) {
        return checkIfFieldShouldBeModified(oldValue, newValue, false);  // Default to false
    }

    private <T> void modifyIfChanged(boolean condition, T value, Consumer<T> setterAction) {
        if (condition) {
            setterAction.accept(value);  // Set the value if condition is true
        }
    }

    private void transactionTesting(String title) {
        // This is for transaction testing
        if (title != null && title.equals("Test")) {
            throw new RuntimeException("All changes in DB should rollback because book title is 'Test'");
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addBook(BookDto bookDto) throws BookAlreadyExistsException {
        if (bookRepository.existsByTitleAndAuthorName(
                StringUtils.trim(bookDto.getTitle()),
                StringUtils.trim(bookDto.getAuthor()))
        ) {
            throw new BookAlreadyExistsException("Book already exists");
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

    /**
     * Use this if you want to use just filtration
     */
    @Override
    public List<BookDto> getBooks(BookSearchQuery bookSearchQuery) {
        List<Book> booksFromDB = bookRepository.findBooksFilteredByFields(
                StringUtils.trimToEmpty(bookSearchQuery.getTitle()),
                StringUtils.trimToEmpty(bookSearchQuery.getAuthor()),
                StringUtils.trimToEmpty(bookSearchQuery.getGenre()),
                StringUtils.trimToEmpty(bookSearchQuery.getKeyword())
        );
        List<BookDto> books = new ArrayList<>();
        for (Book book : booksFromDB) {
            books.add(bookConvertor.toDto(book));
        }
        return books;
    }


    /**
     * Use this if you want to use just pagination
     * @param pageNumber number of the page
     * @param pageSize number of books on the page
     * @return books from DB that are paginated by pageNumber and pageSize
     */
    @Override
    public List<BookDto> getBooksWithPagination(int pageNumber, int pageSize) {
        return bookRepository
                .getBooksWithPagination(pageNumber, pageSize)
                .stream()
                .map(bookConvertor::toDto)
                .toList();
    }


    // Use this in most cases

    /**
     * Use this if you want to use both pagination and filtration
     * @param bookSearchQuery dto to filter users
     * @param pageNumber number of the page
     * @param pageSize number of books on the page
     * @return books from DB that are filtered by fields and paginated by pageNumber and pageSize
     */
    @Override
    public List<BookDto> getBooksWithPaginationAndFiltration(BookSearchQuery bookSearchQuery, int pageNumber, int pageSize) {
        List<Book> booksFromDB;
        if (pageNumber < 1 || pageSize < 1) {
            booksFromDB = bookRepository.findBooksFilteredByFields(
                    StringUtils.trimToEmpty(bookSearchQuery.getTitle()),
                    StringUtils.trimToEmpty(bookSearchQuery.getAuthor()),
                    StringUtils.trimToEmpty(bookSearchQuery.getGenre()),
                    StringUtils.trimToEmpty(bookSearchQuery.getKeyword())
            );
        } else {
            booksFromDB = bookRepository.getBooksWithPaginationAndFilteredByFields(
                    StringUtils.trimToEmpty(bookSearchQuery.getTitle()),
                    StringUtils.trimToEmpty(bookSearchQuery.getAuthor()),
                    StringUtils.trimToEmpty(bookSearchQuery.getGenre()),
                    StringUtils.trimToEmpty(bookSearchQuery.getKeyword()),
                    pageNumber, pageSize
            );
        }
        return booksFromDB.stream().map(bookConvertor::toDto).toList();
    }

    // Use this for PUT in HTTP
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBook(BookDto bookDto) throws BookNotFoundException {
        Book book = bookRepository.findById(UUID.fromString(bookDto.getBookId()))
                .orElseThrow(
                        () -> new BookNotFoundException("Book does not exists")
                );
        book.setTitle(bookDto.getTitle());
        book.getAuthor().setName(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.getKeywords().clear();
        List<Keyword> keywords = keywordService.convertStringsToKeywords(bookDto.getKeywords());
        keywords.forEach(keyword -> keyword.setBook(book));
        book.setKeywords(keywords);
        book.setDescription(bookDto.getDescription());

        bookRepository.saveOrUpdate(book);

        transactionTesting(bookDto.getTitle());
    }

    // Use this for PATCH in HTTP
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateBookPartially(BookDto bookDto) throws BookNotFoundException {
        Book book = bookRepository.findById(UUID.fromString(bookDto.getBookId()))
                .orElseThrow(
                        () -> new BookNotFoundException("Book does not exists")
                );
        modifyIfChanged(checkIfFieldShouldBeModified(book.getTitle(), bookDto.getTitle(), true),
                bookDto.getTitle(), book::setTitle
        );
        modifyIfChanged(checkIfFieldShouldBeModified(book.getAuthor().getName(), bookDto.getAuthor(), true),
                bookDto.getAuthor(), book.getAuthor()::setName
        );
        modifyIfChanged(checkIfFieldShouldBeModified(book.getGenre(), bookDto.getGenre()),
                bookDto.getGenre(), book::setGenre
        );
        modifyIfChanged(checkIfFieldShouldBeModified(book.getDescription(), bookDto.getDescription()),
                bookDto.getDescription(), book::setDescription
        );

        if (bookDto.getKeywords() != null) {
            List<Keyword> keywords = keywordService.convertStringsToKeywords(bookDto.getKeywords());
            keywords.forEach(keyword -> keyword.setBook(book));
            book.setKeywords(keywords);
        }

        bookRepository.saveOrUpdate(book);

        transactionTesting(bookDto.getTitle());
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
