package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.UserEntity;
import com.sparta.eng80.onetoonetracker.repositories.UserRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.UserAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserAppService<UserEntity> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findById() {
        return Optional.empty();
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUserId(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findUserEntityByEmail(email);
    }

    @Override
    public Iterable<UserEntity> findByGroupId(int id) {
        return null;
    }

    @Override
    public Iterable<UserEntity> findByFirstName(String name) {
        return null;
    }

    @Override
    public Iterable<UserEntity> findByLastName(String name) {
        return null;
    }

    @Override
    public Iterable<UserEntity> findByName(String first, String last) {
        return null;
    }
}
