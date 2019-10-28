package com.fit_goal;

import com.fit_goal.domain.EventDto;


public interface EventDao {

    void create(EventDto eventDto);

   /* EventDto findById(Long id);

    void update(EventDto eventDto);

    void delete(EventDto eventDto);*/
}
