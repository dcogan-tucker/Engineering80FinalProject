package com.sparta.eng80.spartaonetooneformtracker.services.interfaces;

import com.sparta.eng80.spartaonetooneformtracker.entities.GroupEntity;

public interface GroupAppService extends ApplicationService<GroupEntity> {

    GroupEntity findByName(String name);
    GroupEntity findByStreamId(int id);
}
