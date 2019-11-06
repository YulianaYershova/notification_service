package com.fit_goal;

import com.fit_goal.domain.AuditDto;
import com.fit_goal.impl.AuditDaoImpl;
import com.fit_goal.util.AuditMongoConverter;
import com.fit_goal.utils.MongoTestHelper;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        mongoTestHelper.deleteAll();
    }

    @Test
    public void createTest() {
        List<Document> documentList = mongoTestHelper.allDocuments();
        assertThat(documentList).isEmpty();

        AuditDto auditDto = createAuditDto();

        auditDao.create(auditDto);

        List<AuditDto> auditDtoList = auditDao.findAll();

        assertThat(auditDtoList.size()).isEqualTo(1);
        assertThat(auditDtoList.get(0)).isEqualTo(auditDto);
    }

    @Test
    public void findAllTest() {
        // save test data in test DB
        mongoTestHelper.importDocuments("/audit.json");

        // obtain saved data
        List<Document> importedDocuments = mongoTestHelper.allDocuments();

        // Convert documents to AuditDto objects
        List<AuditDto> AuditDtoList = getAuditDtoListFromDocuments(importedDocuments);

        // load data from test DB
        List<AuditDto> returnedAuditDtoList = auditDao.findAll();

        assertThat(returnedAuditDtoList).containsExactlyInAnyOrderElementsOf(AuditDtoList);
    }

    private  List<AuditDto> getAuditDtoListFromDocuments(List<Document> documents) {
        return documents.stream().map(AuditMongoConverter::documentToAuditDto).collect(Collectors.toList());
    }

    private AuditDto createAuditDto() {
        return AuditDto.builder()
                .event("event")
                .serviceName("service")
                .date(new Date()).build();
    }
}
