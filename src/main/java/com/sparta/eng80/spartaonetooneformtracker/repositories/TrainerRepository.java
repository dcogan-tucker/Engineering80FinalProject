package com.sparta.eng80.spartaonetooneformtracker.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends CrudRepository<TrainerRepository, Integer> {
}
