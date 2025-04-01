package ua.kpi.jakartaee.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookDTO implements RequestData {
    private String bookId;
    private final String title;
    private final String author;
    private final String genre;
    private List<String> keywords;
    private final String description;

    public BookDTO(String title, String author, String genre, List<String> keywords, String description) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.keywords = keywords;
        this.description = description;
    }
}
