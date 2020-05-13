package com.fitgoal.dao.util;

import com.fitgoal.dao.domain.AuditDto;
import lombok.experimental.UtilityClass;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static com.fitgoal.dao.util.AuditDtoFields.DATE;
import static com.fitgoal.dao.util.AuditDtoFields.EVENT;
import static com.fitgoal.dao.util.AuditDtoFields.SERVICE_NAME;

@UtilityClass
public class AuditMongoConverter {

    public AuditDto documentToAuditDto(Document document) {
        return AuditDto.builder()
                .event(document.getString(EVENT))
                .serviceName(document.getString(SERVICE_NAME))
                .date(extractDate(document)).build();
    }

    public Document auditDtoToDocument(AuditDto auditDto) {
        return new Document(SERVICE_NAME, auditDto.getServiceName())
                .append(EVENT, auditDto.getEvent())
                .append(DATE, auditDto.getDate());
    }

    private LocalDateTime extractDate(Document document) {
        return document.getDate(DATE)
                .toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime()
                .truncatedTo(ChronoUnit.SECONDS);
    }
}
