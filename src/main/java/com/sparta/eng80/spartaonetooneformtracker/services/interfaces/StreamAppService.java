package com.sparta.eng80.spartaonetooneformtracker.services.interfaces;

import com.sparta.eng80.spartaonetooneformtracker.entities.StreamEntity;

public interface StreamAppService extends ApplicationService<StreamEntity> {

    StreamEntity findByName(String name);
}
