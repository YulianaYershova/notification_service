package com.fit_goal;

import com.fit_goal.domain.EventDto;
import org.bson.Document;

public class EventMapper {

    /**
     * Map objects {@link Document} to {@link EventDto}.
     *
     * @param document the information document.
     * @return A object {@link EventDto}.
     */
    public static EventDto map(final Document document) {
        final EventDto eventDto = new EventDto();
        eventDto.setId(document.getLong("_id"));
        eventDto.setEvent(document.getString("event"));
        eventDto.setServiceName(document.getString("service_name"));
        return eventDto;
    }
}
