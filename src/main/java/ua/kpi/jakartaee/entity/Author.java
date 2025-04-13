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
@Table(name = "authors")
public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "id", nullable = false, updatable = false)
        private UUID id;

        @Column(name = "name", nullable = false, unique = true)
        private String name;

        @Column(name = "biography")
        private String biography;

        @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
        @ToString.Exclude
        private List<Book> books;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Author author = (Author) o;
                return Objects.equals(id, author.id) &&
                        Objects.equals(name, author.name) &&
                        Objects.equals(biography, author.biography);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, name, biography);
        }
}
