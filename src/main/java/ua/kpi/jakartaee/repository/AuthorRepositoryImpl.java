package ua.kpi.jakartaee.repository;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionManagement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ua.kpi.jakartaee.entity.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Probably do not need these two annotations: : @TransactionManagement and @TransactionAttribute.
// Their behaviour is applied by default for EJB beans (@Stateless, @Singleton, @Stateful).
// If this annotation is not used, the bean is assumed to have container-managed transaction management.
@TransactionManagement
// If the TransactionAttribute annotation is not specified, and the bean uses container managed transaction demarcation, the semantics of the REQUIRED transaction attribute are assumed.
@TransactionAttribute

@Stateless
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<Author> findById(int id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }


    @Override
    public List<Author> findByName(String name) {
        return em.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", name)
                .getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method performs a case-sensitive search for authors whose name
     * matches the provided partial name.
     */
    @Override
    public List<Author> findByNameSubstring(String name) {
        return em.createNamedQuery("Author.findByNameSubstring", Author.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<Author> findAll() {
        return em.createNamedQuery("Author.findAll", Author.class).getResultList();
    }

    @Override
    public void save(Author author) {
        em.persist(author);
    }

    @Override
    public Optional<Author> update(Author author) {
        return Optional.ofNullable(em.merge(author));
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The removal of the {@link Author} entity is done via a named query.
     */
    @Override
    public int deleteById(UUID authorId) {
        return em.createNamedQuery("Author.deleteById")
                .setParameter("authorId", authorId)
                .executeUpdate();
    }
}
