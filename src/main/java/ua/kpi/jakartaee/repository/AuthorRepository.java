package ua.kpi.jakartaee.repository;

import jakarta.persistence.PersistenceException;
import ua.kpi.jakartaee.entity.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link Author} entities.
 * This interface provides methods for performing CRUD operations
 * and querying authors based on his attributes
 * <p>
 * All methods interacting with the persistence layer return either
 * a result or modify the data in the underlying database.
 */
public interface AuthorRepository {

    /**
     * Finds an {@link Author} entity by its ID.
     * <p>
     * This method retrieves an {@link Author} from the database using the provided {@code id}.
     * If the {@code id} exists, an {@link Author} object is returned; otherwise, an empty {@link Optional}.
     *
     * @param id the ID of the {@link Author} entity to be found
     * @return an {@link Optional} containing the found {@link Author} if present, or {@link Optional#empty()} if no entity was found.
     */
    Optional<Author> findById(int id);

    /**
     * Finds a list of authors by their name.
     * This method searches for authors whose name matches the given parameter.
     *
     * @param name the name of the author(s) to find
     * @return a list of {@link Author} entities that match the provided name
     */
    List<Author> findByName(String name);

    /**
     * Finds a list of authors whose name contains the specified substring.
     *
     * @param name the partial name to search for in authors' names
     * @return a list of {@link Author} entities whose names contain the provided substring
     */
    List<Author> findByNameSubstring(String name);

    /**
     * Retrieves all authors from the database.
     * This method fetches a complete list of all {@link Author} entities stored in the database.
     *
     * @return a list of all {@link Author} objects
     */
    List<Author> findAll();

    /**
     * Persists a new {@link Author} entity in the database.
     * <p>
     * This method adds the given {@link Author} to the persistence context and eventually
     * commits the changes to the database.
     *
     * @param author the {@link Author} entity to save to the database
     * @throws PersistenceException if an error occurs during the persistence operation
     */
    void save(Author author);

    /**
     * Saves a new author to the database.
     * This method persists the provided {@link Author} entity into the database.
     * If the author already exists (based on primary key), it will be updated.
     *
     * @param author the {@link Author} entity to be saved
     */
    Optional<Author> update(Author author);

    /**
     * Deletes the specified author from the database.
     * This method removes the provided {@link Author} entity from the database.
     * The entity must be managed (i.e., part of the persistence context) before calling this method.
     *
     * @param author the {@link Author} entity to be deleted
     */
    void delete(Author author);

    /**
     * Deletes an author from the database by their ID.
     * This method uses the specified author ID to remove the corresponding
     * {@link Author} entity from the database. The entity is identified
     * by the provided ID.
     *
     * @param authorId the UUID of the {@link Author} to be deleted
     * @return the number of entities updated (1 if successful, 0 if no entity with the given ID was found)
     */
    int deleteById(UUID authorId);
}
