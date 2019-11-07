package com.fitgoal.service;

import com.fitgoal.dao.domain.AuditDto;

import java.util.List;
import java.util.Optional;

public interface AuditService {

    void create(AuditDto auditDto);

    Optional<AuditDto> findById(String id);

    List<AuditDto> findAll();

    AuditDto update(AuditDto auditDto);

    void delete(AuditDto auditDto);
}