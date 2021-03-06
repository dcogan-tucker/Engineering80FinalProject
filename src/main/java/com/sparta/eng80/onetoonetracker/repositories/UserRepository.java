package com.sparta.eng80.onetoonetracker.repositories;

import com.sparta.eng80.onetoonetracker.entities.TraineeEntity;
import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE email = ?")
    Optional<UserEntity> findUserEntityByEmailEquals(String email);

    @Query(nativeQuery = true, value = "select email from user")
    Set<String> getAllEmails();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from user where user_id = ?")
    void removeById(int userId);
}
