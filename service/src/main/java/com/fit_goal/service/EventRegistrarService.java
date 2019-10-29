package com.fit_goal.service;

import com.fit_goal.EventDao;
import com.fit_goal.EventRegistrar;
import com.fit_goal.domain.EventDto;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class EventRegistrarService implements EventRegistrar {

    private final EventDao eventDao;

    @Inject
    EventRegistrarService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public void create(EventDto eventDto) {
        eventDao.create(eventDto);
    }

    @Override
    public Optional<EventDto> findById(ObjectId id) {
        return eventDao.findById(id);
    }

    @Override
    public List<EventDto> findAll() {
        return eventDao.findAll();
    }

    @Override
    public EventDto update(EventDto eventDto) {
        return eventDao.update(eventDto);
    }

    @Override
    public void delete(EventDto eventDto) {
        eventDao.delete(eventDto);
    }
}
