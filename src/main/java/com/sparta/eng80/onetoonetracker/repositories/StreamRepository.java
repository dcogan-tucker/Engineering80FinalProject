package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepository extends CrudRepository<StreamEntity, Integer> {
}
