package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserEntityByEmailEquals(String email);
}
