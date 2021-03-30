package com.sparta.eng80.spartaonetooneformtracker.repositories;

import com.sparta.eng80.spartaonetooneformtracker.entities.StreamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepository extends CrudRepository<StreamEntity, Integer> {
}
