package com.fit_goal;

import com.fit_goal.impl.AuditDaoImpl;
import com.mongodb.client.MongoClient;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuditDaoImplTest extends AbstractMongoTestContainersTest {

    private AuditDao auditDao;

    @Before
    public void setUp() throws Exception {
        MongoClient mongoClient = getClient();

        auditDao = new AuditDaoImpl(mongoClient);
    }

    @Test
    public void createTest() {

    }

    @Test
    public void findByIdTest() {
    }

    @Test
    public void findAllTest() {
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void deleteTest() {
    }
}