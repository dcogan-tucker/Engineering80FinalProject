package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TrainerRepository extends CrudRepository<TrainerEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from trainer where trainer_id = ?")
    TrainerEntity findTrainerById(int trainerId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from trainer where trainer_id = ?")
    void deleteTrainerById(int trainerId);
}
