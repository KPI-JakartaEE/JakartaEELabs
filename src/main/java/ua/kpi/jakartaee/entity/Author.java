package ua.kpi.jakartaee.entity;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

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
public class Author {
    // TODO to be implemented
}
