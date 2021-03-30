package com.sparta.eng80.spartaonetooneformtracker.repositories;

import com.sparta.eng80.spartaonetooneformtracker.entities.TraineeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends CrudRepository<TraineeEntity, Integer> {
}
