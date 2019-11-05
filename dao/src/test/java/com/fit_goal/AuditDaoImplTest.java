package com.fit_goal;

import com.fit_goal.domain.AuditDto;
import com.fit_goal.impl.AuditDaoImpl;
import com.fit_goal.utils.MongoTestHelper;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class AuditDaoImplTest extends AbstractMongoTestContainersTest {

    private static final String DATABASE = "notification_db";
    private static final String COLLECTION_NAME = "audit";

    private AuditDao auditDao;
    private MongoTestHelper mongoTestHelper;


    @Before
    public void setUp()  {
        MongoClient mongoClient = getClient();
        auditDao = new AuditDaoImpl(mongoClient);
        mongoTestHelper = new MongoTestHelper(mongoClient, DATABASE, COLLECTION_NAME);
    }

    @Test
    public void createTest() {
        List<Document> documentList = mongoTestHelper.allDocuments();
        assertThat(documentList).isEmpty();
    }


    @Test
    public void findByIdTest() {
        mongoTestHelper.importDocuments("/audit.json");
    }

    @Test
    public void findAllTest() {
        // save test data in test DB
        mongoTestHelper.importDocuments("/audit.json");
        // load data from test DB
        List<AuditDto> list = auditDao.findAll();
    }

    @Test
    public void updateTest() {
        mongoTestHelper.importDocuments("/audit.json");
    }

    @Test
    public void deleteTest() {
        mongoTestHelper.importDocuments("/audit.json");
    }
}
