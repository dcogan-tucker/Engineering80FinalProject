package com.sparta.eng80.onetoonetracker.services;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;
import com.sparta.eng80.onetoonetracker.repositories.StreamRepository;
import com.sparta.eng80.onetoonetracker.services.interfaces.StreamAppService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreamService implements StreamAppService {

    private final StreamRepository streamRepository;

    public StreamService(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    @Override
    public Optional<StreamEntity> findById(int id) {
        return streamRepository.findById(id);
    }

    @Override
    public Iterable<StreamEntity> findAll() {
        return streamRepository.findAll();
    }

    @Override
    public StreamEntity save(StreamEntity streamEntity) {
        return streamRepository.save(streamEntity);
    }

    @Override
    public Optional<StreamEntity> findByName(String name) {
        return null;
    }
}
