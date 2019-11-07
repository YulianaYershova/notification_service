package com.fitgoal.service.impl;

import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.service.AuditService;

import javax.inject.Inject;
import java.util.List;

public class AuditServiceImpl implements AuditService {

    private final AuditDao auditDao;

    @Inject
    AuditServiceImpl(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    @Override
    public void create(AuditDto auditDto) {
        auditDao.create(auditDto);
    }

    @Override
    public List<AuditDto> findAll() {
        return auditDao.findAll();
    }
}
