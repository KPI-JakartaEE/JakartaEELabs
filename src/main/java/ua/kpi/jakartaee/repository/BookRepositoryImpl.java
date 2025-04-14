package ua.kpi.jakartaee.repository;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionManagement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.entity.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Probably do not need these two annotations: @TransactionManagement and @TransactionAttribute.
// Their behaviour is applied by default for EJB beans (@Stateless, @Singleton, @Stateful).
// If this annotation is not used, the bean is assumed to have container-managed transaction management.
@TransactionManagement
// If the TransactionAttribute annotation is not specified, and the bean uses container managed transaction demarcation, the semantics of the REQUIRED transaction attribute are assumed.
@TransactionAttribute

@Stateless
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Book> findById(UUID id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByTitle} from {@link Book} to search for books by their title.
     */
    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByTitle", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This is a case-sensitive implementation.
     * <p>
     * This method uses the named query {@code Book.findByPartOfTitle} from {@link Book}, which relies on a {@code LIKE} clause
     * to perform partial matching of the title.
     */
    @Override
    public List<Book> findByTitleSubstring(String title) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByTitleSubstring", Book.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findAll} from {@link Book} to fetch all records.
     */
    @Override
    public List<Book> findAll() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByAuthorId} from {@link Book} to retrieve books associated with a particular author.
     */
    @Override
    public List<Book> findByAuthorId(UUID authorId) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthorId", Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByAuthorName} from {@link Book},
     * which joins {@link Book} entity with the {@link Author} entity and filters by the author's name.
     */
    @Override
    public List<Book> findByAuthorName(String authorName) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthorName", Book.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByPartOfAuthorName} from {@link Book}, which performs a case-sensitive
     * partial match on the author's name.
     */
    @Override
    public List<Book> findByAuthorNameSubstring(String authorName) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthorNameSubstring", Book.class);
        query.setParameter("authorName", "%" + authorName + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByGenre} from {@link Book}, which compares the genre field using equality.
     */
    @Override
    public List<Book> findByGenre(String genre) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByGenre", Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByPartOfGenre} from {@link Book}, which performs a case-sensitive
     * partial match using the {@code LIKE} operator.
     */
    @Override
    public List<Book> findByGenreSubstring(String genre) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByGenreSubstring", Book.class);
        query.setParameter("genre", "%" + genre + "%");
        return query.getResultList();
    }

    /**
     * <p>
     * This method uses the named query {@code Book.findByKeyword} from {@link Book}, which checks if the given keyword
     * exists in the {@code keywords} list of each book (case-sensitive).
     */
    @Override
    public List<Book> findByKeyword(String keyword) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByKeyword", Book.class);
        query.setParameter("keyword", keyword);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByPartOfKeyword} from {@link Book}, which performs a case-sensitive
     * partial match on the keywords collection.
     */
    @Override
    public List<Book> findByKeywordSubstring(String keyword) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByKeywordSubstring", Book.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public Optional<Book> findByTitleAndAuthorName(String title, String authorName) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByTitleAndAuthorName", Book.class);
        query.setParameter("title", title);
        query.setParameter("authorName", authorName);
        List<Book> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses {@link EntityManager#persist(Object)} to insert a new {@link Book} entity.
     */
    @Override
    public void save(Book book) {
        em.persist(book);
    }


    /**
     * {@inheritDoc}
     * <p>
     * This method uses {@link EntityManager#merge(Object)} to update the given {@link Book} entity.
     * If the book does not already exist (i.e., the ID is not found in the database), it will be inserted as a new entity.
     */
    @Override
    public Optional<Book> saveOrUpdate(Book book) {
        return Optional.ofNullable(em.merge(book));
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses {@link EntityManager#remove(Object)} to remove the given {@link Book} entity from the database.
     * The entity must be managed by the {@link EntityManager} (i.e., it must be in the persistence context) for the removal to succeed.
     * If the entity is not found in the database, no action will be performed.
     */
    @Override
    public void delete(Book book) {
        em.remove(book);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses a named query to delete the {@link Book} with the given {@code id}.
     * If the {@code id} is not found, no rows will be affected in the database.
     */
    @Override
    public int deleteById(UUID id) {
        return em.createNamedQuery("Book.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Book> getBooksWithPagination(int pageNumber, int pageSize) {
        if (pageNumber < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and page size must be greater than 0");
        }
        return em.createQuery("SELECT b FROM Book b", Book.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }


    @Override
    public List<Book> findBooksFilteredByFields(
            String title,
            String authorName,
            String genre,
            String keyword
    ) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findBooksFilteredByAllFields", Book.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("authorName", "%" + authorName + "%");
        query.setParameter("genre", "%" + genre + "%");
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public boolean existsById(UUID id) {
        Long count = em.createNamedQuery("Book.countById", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitle(String title) {
        Long count = em.createNamedQuery("Book.countByTitle", Long.class)
                .setParameter("title", title)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitleAndAuthorId(String title, UUID authorId) {
        Long count = em.createNamedQuery("Book.countByTitleAndAuthorId", Long.class)
                .setParameter("title", title)
                .setParameter("authorId", authorId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitleAndAuthorName(String title, String authorName) {
        Long count = em.createNamedQuery("Book.countByTitleAndAuthorName", Long.class)
                .setParameter("title", title)
                .setParameter("authorName", authorName)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public long countByAuthor(Author author) {
        Long count = em.createNamedQuery("Book.countByAuthor", Long.class)
                .setParameter("author", author)
                .getSingleResult();
        return count == null ? 0 : count;
    }

    @Override
    public long countByAuthorId(UUID authorId) {
        Long count = em.createNamedQuery("Book.countByAuthorId", Long.class)
                .setParameter("authorId", authorId)
                .getSingleResult();
        return count == null ? 0 : count;
    }

    @Override
    public long countByAuthorName(String authorName) {
        Long count = em.createNamedQuery("Book.countByAuthorName", Long.class)
                .setParameter("authorName", authorName)
                .getSingleResult();
        return count == null ? 0 : count;
    }

}
