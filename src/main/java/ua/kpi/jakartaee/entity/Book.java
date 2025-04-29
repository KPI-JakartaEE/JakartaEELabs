package ua.kpi.jakartaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

// TODO To many NamesQueries. Consider removing redundant queries in the future
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Book.findAll",
                query = "SELECT b FROM Book b JOIN FETCH b.author ORDER BY b.title ASC"
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
                query = "SELECT b FROM Book b JOIN b.keywords k WHERE k.keyword = :keyword"
        ),
        @NamedQuery(
                name = "Book.findByKeywordSubstring",
                query = "SELECT b FROM Book b JOIN b.keywords k WHERE k.keyword LIKE :keyword"
        ),

        @NamedQuery(
                name = "Book.findByTitleAndAuthorName",
                query = "SELECT b FROM Book b WHERE b.title = :title AND b.author.name = :authorName"
        ),

        @NamedQuery(
                name = "Book.deleteById",
                query = "DELETE FROM Book b WHERE b.id = :id"
        ),

        @NamedQuery(
                name = "Book.findBooksFilteredByAllFields",
                query = """
                        SELECT DISTINCT b FROM Book b JOIN b.keywords k
                        WHERE
                        UPPER(b.title) LIKE UPPER(:title) AND
                        UPPER(b.author.name) LIKE UPPER(:authorName) AND
                        UPPER(b.genre) LIKE UPPER(:genre) AND
                        UPPER(k.keyword) LIKE UPPER(:keyword)
                        ORDER BY b.title ASC
                        """
        ),

        @NamedQuery(
                name = "Book.countById",
                query = "SELECT COUNT(b) FROM Book b WHERE b.id = :id"
        ),
        @NamedQuery(
                name = "Book.countByTitle",
                query = "SELECT COUNT(b) FROM Book b WHERE b.title = :title"
        ),
        @NamedQuery(
                name = "Book.countByAuthor",
                query = "SELECT COUNT(b) FROM Book b WHERE b.author = :author"
        ),
        @NamedQuery(
                name = "Book.countByAuthorId",
                query = "SELECT COUNT(b) FROM Book b WHERE b.author.id = :authorId"
        ),
        @NamedQuery(
                name = "Book.countByAuthorName",
                query = "SELECT COUNT(b) FROM Book b WHERE b.author.name = :authorName"
        ),
        @NamedQuery(
                name = "Book.countByTitleAndAuthorId",
                query = "SELECT COUNT(b) FROM Book b WHERE b.title = :title AND b.author.id = :authorId"
        ),
        @NamedQuery(
                name = "Book.countByTitleAndAuthorName",
                query = "SELECT COUNT(b) FROM Book b WHERE b.title = :title AND b.author.name = :authorName"
        )
})
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Keyword> keywords;

    // Let`s give it enough length to hold any reasonable text.
    @Column(length = 4096)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, description);
    }
}