package com.sparta.eng80.onetoonetracker.services.interfaces;

import java.util.Optional;

/**
 * Root interface for the applications services.
 *
 * @param <T> The entity the service deals with.
 */
public interface ApplicationService<T> {

    /**
     * Finds the the entity from it's repository by the given id.
     *
     * @param id The id of the entity to find.
     * @return An Optional containing the entity with the given id if found, or empty if none found.
     */
    Optional<T> findById(int id);

    /**
     * Finds all the entities from the repository.
     *
     * @return An Iterable containing all the entities in the repository.
     */
    Iterable<T> findAll();

    /**
     * Saves the given entity to the repository. If the repository already contains an entity with the same id, the
     * given entity is saved over it.
     *
     * @param t The entity to save.
     * @return The entity saved.
     */
    T save(T t);
}
