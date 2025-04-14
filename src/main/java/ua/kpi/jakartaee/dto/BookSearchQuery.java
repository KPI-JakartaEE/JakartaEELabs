package ua.kpi.jakartaee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookSearchQuery {
    private String title;
    private String author;
    private String genre;
    private String keyword;
}
