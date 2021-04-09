package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;

import java.util.Optional;

/**
 * Interface to be implemented by a service dealing with group entities.
 */
public interface GroupAppService extends ApplicationService<GroupEntity> {

    /**
     * Finds the group with the given name from the repository.
     *
     * @param name The name of the group to find.
     * @return An Optional containing the Group with the given name, or empty if not found.
     */
    Optional<GroupEntity> findByName(String name);

    /**
     * Finds all the groups with the given stream id from the repository.
     *
     * @param id The stream id of the groups to find.
     * @return An Iterable containing all the groups that are part of the given stream.
     */
    Iterable<GroupEntity> findByStreamId(int id);
}
