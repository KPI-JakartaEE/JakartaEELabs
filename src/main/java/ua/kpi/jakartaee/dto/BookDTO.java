package ua.kpi.jakartaee.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String bookId;
    @Getter
    private String title;
    @Getter
    private String author;
    @Getter
    private String genre;
    @Getter
    private List<String> keywords;
    @Getter
    private String description;
    private String publicationDate;
}
