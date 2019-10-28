package com.fit_goal.service;

import com.fit_goal.EventDao;
import com.fit_goal.api.EventRegistrar;
import com.fit_goal.domain.EventDto;

import javax.inject.Inject;

public class EventRegistrarService implements EventRegistrar {

    private final EventDao eventDao;

    @Inject
    EventRegistrarService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @Override
    public void registerEvent(EventDto eventDto) {
       // eventDao.create(eventDto);
    }
}
