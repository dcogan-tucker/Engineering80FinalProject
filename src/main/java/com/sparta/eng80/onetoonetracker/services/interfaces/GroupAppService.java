package com.sparta.eng80.onetoonetracker.services.interfaces;

import com.sparta.eng80.onetoonetracker.entities.GroupEntity;

public interface GroupAppService extends ApplicationService<GroupEntity> {

    GroupEntity findByName(String name);
    GroupEntity findByStreamId(int id);
}
