package ua.kpi.jakartaee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String bookId;
    private String title;
    private String author;
    private String publicationDate;
    private String genre;
    private List<String> keywords;
    private String description;
}
