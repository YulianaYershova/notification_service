package com.fit_goal;

import com.fit_goal.api.EventRegistrar;
import com.fit_goal.domain.Event;
import com.fit_goal.domain.EventDto;

import javax.inject.Inject;

public class EventRegistrarService implements EventRegistrar {
    private EventDao eventDao;

    @Inject
    EventRegistrarService(EventDao eventDao) {
        this.eventDao = eventDao;
    }


    @Override
    public void registerEvent(Event event) {
        eventDao.create(new EventDto("r","r"));
    }
}
