package com.sparta.eng80.spartaonetooneformtracker.services.interfaces;

import java.util.Optional;

public interface UserAppService<T> extends ApplicationService<T> {

    Optional<T> findByUserId(int id);
    Optional<T> findByEmail(String name);
    Iterable<T> findByGroupId(int id);
    Iterable<T> findByFirstName(String name);
    Iterable<T> findByLastName(String name);
    Iterable<T> findByName(String first, String last);
}
