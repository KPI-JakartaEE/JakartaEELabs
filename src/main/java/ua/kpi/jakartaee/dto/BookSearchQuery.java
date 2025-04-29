package ua.kpi.jakartaee.dto;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookSearchQuery {
    @QueryParam("title")
    private String title;
    @QueryParam("author")
    private String author;
    @QueryParam("genre")
    private String genre;
    @QueryParam("keyword")
    private String keyword;
}
