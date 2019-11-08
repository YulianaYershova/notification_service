package com.fitgoal.dao;

import com.fitgoal.dao.domain.AuditDto;

import java.util.List;

public interface AuditDao {

    AuditDto create(AuditDto auditDto);

    List<AuditDto> findAll();
}
