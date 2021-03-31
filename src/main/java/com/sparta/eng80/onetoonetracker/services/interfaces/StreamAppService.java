package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;

import java.util.Optional;

/**
 * Interface to be implemented by a service dealing with stream entities.
 */
public interface StreamAppService extends ApplicationService<StreamEntity> {

    /**
     * Finds the stream with the given name from the repository.
     *
     * @param name The name of the stream to find.
     * @return An Optional containing the the stream with the given name, or empty if none found.
     */
    Optional<StreamEntity> findByName(String name);
}
