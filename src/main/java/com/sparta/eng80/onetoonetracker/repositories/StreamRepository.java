package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreamRepository extends CrudRepository<StreamEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM stream WHERE stream_name = ?")
    Optional<StreamEntity> findByName(String name);

}
