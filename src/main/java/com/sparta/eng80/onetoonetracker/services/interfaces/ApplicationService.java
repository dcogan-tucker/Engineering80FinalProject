package com.sparta.eng80.onetoonetracker.services.interfaces;

import java.util.Optional;

public interface ApplicationService<T> {

    Optional<T> findById(int id);
    Iterable<T> findAll();
    T save(T t);
}
