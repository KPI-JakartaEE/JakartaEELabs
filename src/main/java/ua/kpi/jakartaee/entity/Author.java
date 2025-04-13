package ua.kpi.jakartaee.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Author.findAll",
                query = "SELECT a FROM Author a"
        ),
        @NamedQuery(
                name = "Author.findByName",
                query = "SELECT a FROM Author a WHERE a.name = :name"
        ),
        @NamedQuery(
                name = "Author.findByNameSubstring",
                query = "SELECT a FROM Author a WHERE a.name LIKE :name"
        ),
        @NamedQuery(
                name = "Author.deleteById",
                query = "DELETE FROM Author a WHERE a.id = :authorId"
        )
})
@Entity
@Table(name = "book_authors")
public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String name;

        public Author(String name) {
                this.name = name;
        }
}
