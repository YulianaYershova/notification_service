package com.fitgoal.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Builder
public class AuditDto {

    private String serviceName;

    private String event;

    private Date date;
}
