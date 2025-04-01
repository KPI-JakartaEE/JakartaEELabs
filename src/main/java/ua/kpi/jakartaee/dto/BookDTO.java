package ua.kpi.jakartaee.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private List<String> keywords;
    private String description;
    private String publicationDate;
}
