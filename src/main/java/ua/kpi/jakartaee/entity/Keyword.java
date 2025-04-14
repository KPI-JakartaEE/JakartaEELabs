package ua.kpi.jakartaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "book_keywords")
@IdClass(Keyword.KeywordId.class)
public class Keyword {

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @Id
    @Column(name = "keyword")
    private String keyword;

    /* This is for composite key
    * Name and type of fields in KeywordId should be exactly like this otherwise it won't work
    * Keyword.Book book -> KeywordId.UUID book
    * Keyword.String keyword -> KeywordId.String keyword
    * */
    @Data
    @AllArgsConstructor
    public static class KeywordId implements Serializable {
        private UUID book;
        private String keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword1 = (Keyword) o;
        return Objects.equals(book, keyword1.book) && Objects.equals(keyword, keyword1.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, keyword);
    }
}
