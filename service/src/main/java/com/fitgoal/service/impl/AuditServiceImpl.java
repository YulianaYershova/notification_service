package com.fitgoal.service.impl;

import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.service.AuditService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

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

   /* @Override
    public Optional<AuditDto> findById(String id) {
        return auditDao.findById(id);
    }*/

    @Override
    public List<AuditDto> findAll() {
        return auditDao.findAll();
    }

  /*  @Override
    public AuditDto update(AuditDto auditDto) {
        return auditDao.update(auditDto);
    }

    @Override
    public void delete(AuditDto auditDto) {
        auditDao.delete(auditDto);
    }*/
}
