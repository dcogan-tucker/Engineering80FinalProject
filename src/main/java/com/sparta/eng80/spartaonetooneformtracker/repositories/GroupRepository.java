package com.sparta.eng80.spartaonetooneformtracker.repositories;

import com.sparta.eng80.spartaonetooneformtracker.entities.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Integer> {
}
