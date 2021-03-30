package com.sparta.eng80.spartaonetooneformtracker.services.interfaces;

import java.util.Optional;

public interface ApplicationService<T> {

    Optional<T> findById();
    Iterable<T> findAll();
    T save(T t);
}
