package com.sparta.eng80.spartaonetooneformtracker.repositories;

import com.sparta.eng80.spartaonetooneformtracker.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
