package com.fit_goal.impl;

import com.fit_goal.EventDao;
import com.fit_goal.domain.EventDto;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;

public class EventDaoImpl extends AbstractDAO<EventDto> implements EventDao {

    public EventDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public EventDto findById(Long id) {
        return get(id);
    }

    @Override
    public long create(EventDto eventDto) {
        return persist(eventDto).getId();
    }

    @Override
    public void update(EventDto eventDto) {

    }
    @Override
    public void delete(EventDto eventDto) {

    }
}
