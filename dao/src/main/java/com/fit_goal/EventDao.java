package com.fit_goal;

import com.fit_goal.domain.EventDto;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;


public interface EventDao {

    void create(EventDto eventDto);

    Optional<EventDto>  findById(ObjectId id);

    List<EventDto> findAll();

    EventDto update(EventDto eventDto);

    void delete(EventDto eventDto);
}
