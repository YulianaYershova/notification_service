package com.fit_goal;

import com.fit_goal.domain.AuditDto;

import java.util.List;
import java.util.Optional;

public interface AuditDao {

    void create(AuditDto auditDto);

    List<AuditDto> findAll();
}
