package com.fit_goal;

import com.fit_goal.domain.EventDto;


public interface EventDao {

    EventDto findById(Long id);

    long create(EventDto eventDto);

    void update(EventDto eventDto);

    void delete(EventDto eventDto);
}
