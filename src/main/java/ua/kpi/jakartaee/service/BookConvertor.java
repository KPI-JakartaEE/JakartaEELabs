package ua.kpi.jakartaee.service;

import jakarta.ejb.*;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.entity.Author;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.entity.Keyword;
import ua.kpi.jakartaee.repository.AuthorRepository;

import java.util.List;

@Stateless
@LocalBean
public class BookConvertor implements Convertor<BookDto, Book> {
    @EJB
    private AuthorRepository authorRepository;

    @EJB
    private KeywordService keywordService;

    @Override
    public BookDto toDto(Book book) {
        List<String> keywords = keywordService.convertKeywordsToStrings(book.getKeywords());
        return BookDto.builder()
                .bookId(book.getId().toString())
                .title(book.getTitle())
                .author(book.getAuthor().getName())
                .genre(book.getGenre())
                .keywords(keywords)
                .description(book.getDescription())
                .build();
    }

    @Override
    public Book toEntity(BookDto bookDto) {
        if (bookDto == null) {
            throw new IllegalArgumentException("bookDto is null");
        }

        Author author = authorRepository
                .findByName(bookDto.getAuthor())
                .orElse(new Author(bookDto.getAuthor()));

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(author)
                .genre(bookDto.getGenre())
                .description(bookDto.getDescription())
                .build();

        List<Keyword> keywords = keywordService.convertStringsToKeywords(bookDto.getKeywords());
        keywords.forEach(keyword -> keyword.setBook(book));
        book.setKeywords(keywords);

        return book;
    }
}
