package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends CrudRepository<TraineeEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM trainee WHERE group_id = ?")
    Iterable<TraineeEntity> getAllTraineesFromAGroup(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM trainee WHERE trainee_id = ?")
    Optional<TraineeEntity> findById(int id);

}
