package com.fit_goal.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class EventDto {

    @Id
    private Long id;

    @NotNull
    private String serviceName;

    @NotNull
    private String event;


    public EventDto(@NotNull String serviceName, @NotNull String event) {
        this.serviceName = serviceName;
        this.event = event;
    }
}
