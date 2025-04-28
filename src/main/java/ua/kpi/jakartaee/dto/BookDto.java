package ua.kpi.jakartaee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String bookId;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String genre;
    private List<String> keywords;
    private String description;
}
