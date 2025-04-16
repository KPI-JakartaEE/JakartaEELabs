package ua.kpi.jakartaee.service;

import jakarta.ejb.*;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.entity.Author;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.entity.Keyword;
import ua.kpi.jakartaee.repository.AuthorRepository;
import ua.kpi.jakartaee.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
@LocalBean
public class BookConvertor implements Convertor<BookDto, Book> {
    @EJB
    private AuthorRepository authorRepository;

    @EJB
    private BookRepository bookRepository;

    public List<String> filterKeywords(List<String> keywords) {
        return keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isBlank())
                .distinct()
                .collect(Collectors.toList());
    }

    // This should be public if you want transactions to work. Transactions may not work if it is private
    // TODO There is probably a better way to have transaction rather than creating public methods that are used only in this class

    /**
     * If author with such id is present in DB he/she is being retrieved, otherwise new author is created
     * Updates author name
     *
     * @param oldAuthorName old of the author to get
     * @param authorName new name of the author
     * @return new or author that is already in DB
     */
    public Author getOrCreateAuthor(String oldAuthorName, String authorName) {
        Author author = new Author(authorName);
        if (oldAuthorName != null) {
            author = authorRepository
                    .findByName(oldAuthorName)
                    .orElse(author);
        }
        author.setName(authorName);
        return author;
    }

    // This should be public if you want transactions to work. Transactions may not work if it is private

    /**
     * If Book with such id is already in DB then it is being retrieved, otherwise new Book is being created
     * Updates book if necessary
     *
     * @param bookDto bookDto that is used to create Book entity
     * @param author author of the book
     * @return newly created or retrieved from DB Book
     */
    public Book createOrUpdateBook(BookDto bookDto, Author author) {
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(author)
                .genre(bookDto.getGenre())
                .keywords(new ArrayList<>())
                .description(bookDto.getDescription())
                .build();
        if (bookDto.getBookId() != null) {
            book = bookRepository.findById(UUID.fromString(bookDto.getBookId())).orElse(book);
            if (bookRepository.existsById(book.getId())) {
                book.setTitle(bookDto.getTitle());
                book.setGenre(bookDto.getGenre());
                book.setDescription(bookDto.getDescription());
            }
        }
        return book;
    }


    /**
     * Converts entity to DTO
     * @param book book entity
     * @return book dto
     */
    @Override
    public BookDto toDto(Book book) {

        List<String> keywords = book.getKeywords().stream()
                .map(Keyword::getKeyword)
                .toList();

        return BookDto.builder()
                .bookId(book.getId().toString())
                .title(book.getTitle())
                .oldAuthorName(book.getAuthor().getName())
                .author(book.getAuthor().getName())
                .genre(book.getGenre())
                .keywords(keywords)
                .description(book.getDescription())
                .build();
    }

    /**
     * Converts BookDto to Book entity that will be saved in DB
     *
     * @param bookDto book that is received from FrontEnd
     * @return book as entity
     */
    @Override
    public Book toEntity(BookDto bookDto) {
        if (bookDto == null) {
            throw new IllegalArgumentException("bookDto is null");
        }

        Author author = getOrCreateAuthor(bookDto.getOldAuthorName(), bookDto.getAuthor());
        Book book = createOrUpdateBook(bookDto, author);

        // Converts keywords from String to Keyword type
        List<Keyword> keywords = filterKeywords(bookDto.getKeywords()).stream()
                .map(keywordString -> {
                    Keyword keyword = new Keyword();
                    keyword.setKeyword(keywordString);
                    keyword.setBook(book);
                    return keyword;
                })
                .toList();

        // Removes keywords from DB if book is in managed state (a.k.a object was previously retrieved from DB)
        // Object state is identified in method createOrUpdateBook
        book.getKeywords().clear();

        // Adds new keywords to DB if object is in managed state
        book.setKeywords(keywords);

        return book;
    }
}
