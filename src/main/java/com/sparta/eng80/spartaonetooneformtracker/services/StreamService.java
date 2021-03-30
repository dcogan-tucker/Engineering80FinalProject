package com.sparta.eng80.spartaonetooneformtracker.services;

import com.sparta.eng80.spartaonetooneformtracker.entities.StreamEntity;
import com.sparta.eng80.spartaonetooneformtracker.services.interfaces.StreamAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreamService implements StreamAppService {

    @Override
    public Optional<StreamEntity> findById() {
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
