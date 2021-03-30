package com.sparta.eng80.spartaonetooneformtracker.repositories;

import com.sparta.eng80.spartaonetooneformtracker.entities.FeedbackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackEntity, Integer> {
}
