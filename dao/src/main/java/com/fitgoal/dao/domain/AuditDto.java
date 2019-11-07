package com.fitgoal.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDto {

    private String id;

    private String serviceName;

    private String event;

    private LocalDateTime date;


    public AuditDto(String serviceName, String event, LocalDateTime date) {
        this.serviceName = serviceName;
        this.event = event;
        this.date = date;
    }
}
