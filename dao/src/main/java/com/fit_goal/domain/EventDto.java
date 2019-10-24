package com.fit_goal.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
public class EventDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "service_name")
    private String serviceName;

    @NotNull
    private String event;

    @NotNull
    private LocalDateTime time;


    public EventDto(@NotNull String serviceName, @NotNull String event, @NotNull LocalDateTime time) {
        this.serviceName = serviceName;
        this.event = event;
        this.time = time;
    }

}
