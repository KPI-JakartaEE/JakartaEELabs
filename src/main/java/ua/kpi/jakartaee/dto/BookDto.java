package ua.kpi.jakartaee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String bookId;

    @NotBlank
    private final String title;

    @NotBlank
    private final String author;

    private final String genre;
    private List<String> keywords;
    private final String description;
}
