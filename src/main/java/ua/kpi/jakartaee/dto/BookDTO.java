package ua.kpi.jakartaee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String description;
}
