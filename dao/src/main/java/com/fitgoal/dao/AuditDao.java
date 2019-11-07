package com.fitgoal.dao;

import com.fitgoal.dao.domain.AuditDto;

import java.util.List;
import java.util.Optional;

public interface AuditDao {

    void create(AuditDto auditDto);

    List<AuditDto> findAll();
}
