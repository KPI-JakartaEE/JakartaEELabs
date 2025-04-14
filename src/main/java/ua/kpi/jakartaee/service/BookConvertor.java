package ua.kpi.jakartaee.service;

import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.entity.Book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookConvertor {

    public static List<String> filterKeywords(List<String> keywords) {
        return keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isBlank())
                .collect(Collectors.toList());
    }
    public static Book toBook(BookDto bookDto) {
        bookDto.setBookId(UUID.randomUUID().toString());
        bookDto.setKeywords(filterKeywords(bookDto.getKeywords()));
        return new Book(bookDto);
    }
    public static BookDto toBookDto(Book book) {
        return new BookDto(
                book.getTitle(),
                book.getAuthor().getName(),
                book.getGenre(),
                book.getKeywords(),
                book.getDescription()
        );
    }
}
