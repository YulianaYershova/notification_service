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

    private String serviceName;

    private String event;

    private LocalDateTime date;

}
