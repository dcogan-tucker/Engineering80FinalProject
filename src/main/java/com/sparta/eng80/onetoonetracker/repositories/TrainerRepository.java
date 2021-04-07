package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.TrainerEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TrainerRepository extends CrudRepository<TrainerEntity, Integer> {

    @Query(nativeQuery = true, value = "select * from trainer where trainer_id = ?")
    Optional<TrainerEntity> findTrainerById(int trainerId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from trainer where trainer_id = ?")
    void deleteTrainerById(int trainerId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update trainer set first_name = ?, last_name = ? where trainer_id = ?")
    void editTrainerById(String firstName, String lastName, int trainerId);

    //Testing purposes only
    @Query(nativeQuery = true, value = "select * from trainer where first_name = ? and last_name = ?")
    TrainerEntity findByName(String firstName, String lastName);
}
