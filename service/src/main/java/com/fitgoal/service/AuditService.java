package com.fitgoal.service;


import com.fitgoal.dao.domain.AuditDto;

import java.util.List;

public interface AuditService {

    void create(AuditDto auditDto);

    List<AuditDto> findAll();
}

