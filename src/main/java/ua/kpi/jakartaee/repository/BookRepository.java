package ua.kpi.jakartaee.repository;

import jakarta.persistence.EntityExistsException;
import ua.kpi.jakartaee.entity.Author;
import ua.kpi.jakartaee.entity.Book;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Book} entities.
 * This interface provides methods for performing CRUD operations
 * and querying books based on various attributes such as title,
 * author, genre, and keyword.
 * <p>
 * All methods interacting with the persistence layer return either
 * a result or modify the data in the underlying database.
 */
public interface BookRepository {

    /**
     * Finds a {@link Book} by its ID.
     *
     * @param id the unique identifier of the {@link Book}
     * @return an {@link Optional} containing the found {@link Book} if it exists, or an empty {@link Optional} if not
     */
    Optional<Book> findById(UUID id);

    /**
     * Finds all {@link Book} entities with the exact given title
     *
     * @param title the exact title of the book(s) to search for
     * @return a list of {@link Book} entities matching the given title; the list may be empty if no matches are found
     */
    List<Book> findByTitle(String title);

    /**
     * Finds all {@link Book} entities whose titles contain the given substring
     *
     * @param title the substring to search for within book titles
     * @return a list of {@link Book} entities whose titles contain the given substring;
     *         the list may be empty if no matches are found
     */
    List<Book> findByTitleSubstring(String title);

    /**
     * Retrieves all {@link Book} entities from the database.
     *
     * @return a list of all {@link Book} entities; the list may be empty if no books are found
     */
    List<Book> findAll();

    /**
     * Finds all {@link Book} entities written by the author with the specified ID.
     *
     * @param authorId the UUID of the author whose books are to be retrieved
     * @return a list of {@link Book} entities written by the specified author;
     *         the list may be empty if the author has no books or does not exist
     */
    List<Book> findByAuthorId(UUID authorId);

    /**
     * Finds all {@link Book} entities written by an author with the specified name.
     *
     * @param authorName the name of the author whose books should be retrieved
     * @return a list of {@link Book} entities written by the specified author;
     *         the list may be empty if no matching author or books are found
     */
    List<Book> findByAuthorName(String authorName);

    /**
     * Finds all {@link Book} entities written by an author whose name contains the specified substring.
     *
     * @param authorName a substring of the author's name to search for
     * @return a list of {@link Book} entities written by authors whose names contain the given substring;
     *         the list may be empty if no matches are found
     */
    List<Book> findByAuthorNameSubstring(String authorName);

    /**
     * Finds all {@link Book} entities that match the specified genre exactly.
     *
     * @param genre the genre to search for
     * @return a list of {@link Book} entities that belong to the specified genre;
     *         the list may be empty if no matches are found
     */
    List<Book> findByGenre(String genre);

    /**
     * Finds all {@link Book} entities whose genre contains the specified substring.
     *
     * @param genre a part of the genre to search for
     * @return a list of {@link Book} entities whose genres contain the given substring;
     *         the list may be empty if no matches are found
     */
    List<Book> findByGenreSubstring(String genre);

    /**
     * Finds all {@link Book} entities that contain the specified keyword in their keywords collection.
     *
     * @param keyword the keyword to search for
     * @return a list of {@link Book} entities that have the given keyword;
     *         the list may be empty if no matches are found
     */
    List<Book> findByKeyword(String keyword);

    /**
     * Finds all {@link Book} entities whose keywords contain the specified substring.
     *
     * @param keyword a part of the keyword to search for
     * @return a list of {@link Book} entities whose keywords contain the given substring;
     *         the list may be empty if no matches are found
     */
    List<Book> findByKeywordSubstring(String keyword);

    Optional<Book> findByTitleAndAuthorName(String title, String authorName);

    /**
     * Saves a {@link Book} entity to the database.
     * If the book entity already exists (has a non-null ID), it will throw an {@link EntityExistsException}.
     * Otherwise, the book will be added to the database.
     *
     * @param book the {@link Book} entity to be saved
     * @throws IllegalArgumentException if the entity is not an entity or if it is already removed
     * @throws EntityExistsException if the book entity already exists
     */
    void save(Book book);

    /**
     * Updates an existing {@link Book} entity in the database.
     * <p>
     * This method can be used to save entity as well. As a result returns entity in the managed state (entity is in persistence context)
     *
     * @param book the {@link Book} entity to be updated
     * @return an {@link Optional} containing the updated {@link Book} entity,
     *         or an empty {@link Optional} if the entity could not be updated
     */
    Optional<Book> saveOrUpdate(Book book);

    /**
     * Deletes a {@link Book} entity from the database.
     *
     * @param book the {@link Book} entity to be deleted
     * @throws IllegalArgumentException if the entity is not an entity or is already removed
     */
    void delete(Book book);

    /**
     * Deletes a {@link Book} entity by its ID from the database.
     * <p>
     * The method returns the number of entities deleted. If no entities are deleted (i.e., the
     * provided ID does not exist in the database), the method will return 0.
     *
     * @param id the ID of the {@link Book} entity to be deleted
     * @return the number of entities deleted (1 if successful, 0 if no entity with the given ID was found)
     */
    int deleteById(UUID id);

    /**
     * Retrieves a paginated list of books.
     * <p>
     * This method retrieves books with pagination support, where you can specify the page number
     * and the number of results per page (page size). It is useful when dealing with large datasets
     * to load results in chunks rather than fetching all records at once.
     *
     * @param pageNumber The number of the page to retrieve (1-based index).
     *                   For example, `1` for the first page, `2` for the second page, and so on.
     * @param pageSize The number of results to be returned per page.
     *                 For example, `10` for 10 results per page, `20` for 20 results per page.
     * @throws IllegalArgumentException if pageNumber or pageSize < 0
     * @return A list of `Book` objects representing the books for the requested page.
     *         The list may contain fewer results if the page number exceeds the number of available books.
     */
    List<Book> getBooksWithPagination(int pageNumber, int pageSize);

    /**
     * Retrieves a list of books filtered by the specified fields.
     * <p>
     * This method allows filtering of books based on the provided fields:
     * title, author name, genre, and keyword. The search is case-sensitive.
     * If a field is left empty (null or empty string), it will not be considered in the filtering.
     *
     * @param title The title of the book to search for. If empty or null, this filter will be ignored.
     * @param authorName The name of the author to search for. If empty or null, this filter will be ignored.
     * @param genre The genre of the book to search for. If empty or null, this filter will be ignored.
     * @param keyword A keyword to search for in the book's keywords. If empty or null, this filter will be ignored.
     * @return A list of books that match the given filters. If no books match, an empty list is returned.
     */
    List<Book> findBooksFilteredByFields(
            String title,
            String authorName,
            String genre,
            String keyword
    );

    List<Book> getBooksWithPaginationAndFilteredByFields(
            String title,
            String authorName,
            String genre,
            String keyword,
            int pageNumber,
            int pageSize
    );

    boolean existsById(UUID id);
    boolean existsByTitle(String title);
    boolean existsByTitleAndAuthorId(String title, UUID authorId);
    boolean existsByTitleAndAuthorName(String title, String authorName);

    long countByAuthor(Author author);
    long countByAuthorId(UUID authorId);
    long countByAuthorName(String authorName);
}
