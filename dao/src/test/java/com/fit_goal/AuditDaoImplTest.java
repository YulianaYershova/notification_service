package com.fit_goal;

import com.fit_goal.domain.AuditDto;
import com.fit_goal.impl.AuditDaoImpl;
import com.fit_goal.utils.MongoTestHelper;
import com.mongodb.client.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class AuditDaoImplTest extends AbstractMongoTestContainersTest {

    private static final String DATABASE = "notification_db";
    private static final String COLLECTION_NAME = "audit";

    private AuditDao auditDao;
    private MongoTestHelper mongoTestHelper;


    @Before
    public void setUp() throws Exception {
        MongoClient mongoClient = getClient();
        auditDao = new AuditDaoImpl(mongoClient);
        mongoTestHelper = new MongoTestHelper(mongoClient, DATABASE, COLLECTION_NAME);
    }

    @Test
    public void createTest() {
        // save test data in test DB
        mongoTestHelper.importDocuments("/audit.json");
        List<AuditDto> list= auditDao.findAll();
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
