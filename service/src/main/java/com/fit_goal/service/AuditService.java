package com.fit_goal.service;

import com.fit_goal.Audit;
import com.fit_goal.AuditDao;
import com.fit_goal.domain.AuditDto;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class AuditService implements Audit {

    private final AuditDao auditDao;

    @Inject
    AuditService(AuditDao auditDao) {
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
