package com.sparta.eng80.onetoonetracker.services.interfaces;

import java.util.Optional;

/**
 * Interface for use by any user services.
 * @param <T> The entity for the user.
 */
public interface UserAppService<T> extends ApplicationService<T> {

    /**
     * Finds the user entity by the user's main id from the repository.
     *
     * @param id The main id of the user to find.
     * @return An Optional containing the user entity with the given user id, or empty if none found.
     */
    Optional<T> findByUserId(int id);

    /**
     * Finds the user by email address from the repository.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the user entity with the given email, or empty if none found.
     */
    Optional<T> findByEmail(String email);

    /**
     * Finds all the users in the group with the given id from the repository.
     *
     * @param id The id of the group of the users to find.
     * @return An Iterable containing all the users in the given group.
     */
    Iterable<T> findByGroupId(int id);

    /**
     * Finds all the users with the given first name from the repository.
     *
     * @param name The first name of the users to find.
     * @return An Iterable containing all the users with the given first name.
     */
    Iterable<T> findByFirstName(String name);

    /**
     * Finds all the users with the given last name from the repository.
     *
     * @param name The last name of the users to find.
     * @return An Iterable containing all the users with the given first name.
     */
    Iterable<T> findByLastName(String name);

    /**
     * Finds all the users with the given first and last name from the repository.
     *
     * @param first The first name of the users to find.
     * @param last The last name of the users to find.
     * @return An Iterable containing all the users with the given first and last name.
     */
    Iterable<T> findByName(String first, String last);
}
