package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.FeedbackEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackEntity, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM feedback WHERE feedback_id = ?")
    void removeById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM feedback WHERE trainee_stop = ?")
    Optional<FeedbackEntity> findByTraineeStop(String traineeStop);

}
