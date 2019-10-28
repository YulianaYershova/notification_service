package com.fit_goal.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class EventDto {


    private ObjectId id;

    private String serviceName;

    private String event;

    private LocalDateTime date;


    public EventDto( String serviceName,  String event, LocalDateTime date) {
        this.serviceName = serviceName;
        this.event = event;
        this.date = date;
    }

}
