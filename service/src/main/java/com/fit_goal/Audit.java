package com.fit_goal;

import com.fit_goal.domain.AuditDto;

import java.util.List;
import java.util.Optional;

public interface Audit {

    void create(AuditDto auditDto);

/*
    Optional<AuditDto> findById(String id);
*/

    List<AuditDto> findAll();

   /* AuditDto update(AuditDto auditDto);

    void delete(AuditDto auditDto);*/
}

