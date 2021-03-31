package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;
import com.sparta.eng80.onetoonetracker.services.interfaces.StreamAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreamService implements StreamAppService {

    @Override
    public Optional<StreamEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<StreamEntity> findAll() {
        return null;
    }

    @Override
    public StreamEntity save(StreamEntity streamEntity) {
        return null;
    }

    @Override
    public StreamEntity findByName(String name) {
        return null;
    }
}
