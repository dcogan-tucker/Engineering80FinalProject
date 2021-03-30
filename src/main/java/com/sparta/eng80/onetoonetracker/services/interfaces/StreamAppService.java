package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.StreamEntity;

public interface StreamAppService extends ApplicationService<StreamEntity> {

    StreamEntity findByName(String name);
}
