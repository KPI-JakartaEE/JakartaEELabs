package ua.kpi.jakartaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Book.findAll",
                query = "SELECT b FROM Book b"
        ),
        @NamedQuery(
                name = "Book.findByTitle",
                query = "SELECT b FROM Book b WHERE b.title = :title"
        ),
        @NamedQuery(
                name = "Book.findByTitleSubstring",
                query = "SELECT b FROM Book b WHERE b.title LIKE :title"
        ),

        @NamedQuery(
                name = "Book.findByAuthorId",
                query = "SELECT b FROM Book b WHERE b.author.id = :authorId"
        ),
        @NamedQuery(
                name = "Book.findByAuthorName",
                query = "SELECT b FROM Book b WHERE b.author.name = :authorName"
        ),
        @NamedQuery(
                name = "Book.findByAuthorNameSubstring",
                query = "SELECT b FROM Book b WHERE b.author.name LIKE :authorName"
        ),

        @NamedQuery(
                name = "Book.findByGenre",
                query = "SELECT b FROM Book b WHERE b.genre = :genre"
        ),
        @NamedQuery(
                name = "Book.findByGenreSubstring",
                query = "SELECT b FROM Book b WHERE b.genre LIKE :genre"
        ),

        @NamedQuery(
                name = "Book.findByKeyword",
                query = "SELECT b FROM Book b JOIN b.keywords k WHERE k = :keyword"
        ),
        @NamedQuery(
                name = "Book.findByKeywordSubstring",
                query = "SELECT b FROM Book b JOIN b.keywords k WHERE k LIKE :keyword"
        ),

        @NamedQuery(
                name = "Book.deleteById",
                query = "DELETE FROM Book b WHERE b.id = :bookId"
        ),

        @NamedQuery(
                name = "Book.findBooksFilteredByAllFields",
                query = """
                        SELECT b FROM Book b JOIN b.keywords k
                        WHERE
                        b.title LIKE :title AND
                        b.author.name LIKE :authorName AND
                        b.genre LIKE :genre AND
                        k LIKE :keyword
                        """
        )
})
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre")
    private String genre;

    @ElementCollection
    @CollectionTable(name = "book_keywords", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "keyword")
    private List<String> keywords;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(keywords, book.keywords) && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, keywords, description);
    }
}