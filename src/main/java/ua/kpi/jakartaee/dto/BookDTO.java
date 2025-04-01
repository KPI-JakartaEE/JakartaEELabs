package ua.kpi.jakartaee.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String bookId;
    private final String title;
    private final String author;
    private final String genre;
    private final List<String> keywords;
    private final String description;
    private final String publicationDate;
}
