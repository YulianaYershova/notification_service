package com.fitgoal.dao.impl;

import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.dao.impl.utils.MongoTestHelper;
import com.mongodb.client.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AuditDaoImplTest extends AbstractMongoTestContainersTest {

    private static final String DATABASE = "notification_db";
    private static final String COLLECTION_NAME = "audit";

    private AuditDao auditDao;
    private MongoTestHelper mongoTestHelper;

    @Before
    public void before() {
        MongoClient mongoClient = getClient();
        auditDao = new AuditDaoImpl(mongoClient);
        mongoTestHelper = new MongoTestHelper(mongoClient, DATABASE, COLLECTION_NAME);
        mongoTestHelper.deleteAll();
    }

    @Test
    public void createTest() {
        assertThat(mongoTestHelper.allData()).isEmpty();

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
        List<AuditDto> AuditDtoList = mongoTestHelper.allData();

        // load data from test DB
        List<AuditDto> returnedAuditDtoList = auditDao.findAll();

        assertThat(returnedAuditDtoList).containsExactlyInAnyOrderElementsOf(AuditDtoList);
    }

    private AuditDto createAuditDto() {
        return AuditDto.builder()
                .event("event")
                .serviceName("service")
                .date(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)).build();
    }
}