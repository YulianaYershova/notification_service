package com.fit_goal.util;

import com.fit_goal.domain.EventDto;
import lombok.experimental.UtilityClass;
import org.bson.Document;

import java.time.ZoneId;

import static com.fit_goal.EventDtoFields.*;


@UtilityClass
public class EventMongoConverter {

    public EventDto documentToEventDto(Document document) {
        EventDto eventDto = new EventDto();
        eventDto.setId(document.getObjectId(ID));
        eventDto.setEvent(document.getString(EVENT));
        eventDto.setServiceName(document.getString(SERVICE_NAME));
        eventDto.setDate(document.getDate(DATE).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return eventDto;
    }

    public Document eventDtoToDocument(EventDto eventDto) {
        return new Document(SERVICE_NAME, eventDto.getServiceName())
                .append(EVENT, eventDto.getEvent())
                .append(DATE, eventDto.getDate());
    }
}
