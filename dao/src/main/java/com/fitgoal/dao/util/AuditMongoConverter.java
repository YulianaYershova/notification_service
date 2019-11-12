package com.fitgoal.dao.util;

import com.fitgoal.dao.domain.AuditDto;
import lombok.experimental.UtilityClass;
import org.bson.Document;

import java.util.Date;

import static com.fitgoal.dao.util.AuditDtoFields.*;

@UtilityClass
public class AuditMongoConverter {

    public static AuditDto documentToAuditDto(Document document) {
        return AuditDto.builder()
                .event(document.getString(EVENT))
                .serviceName(document.getString(SERVICE_NAME))
                .date(extractDate(document)).build();
    }

    public static Document auditDtoToDocument(AuditDto auditDto) {
        return new Document(SERVICE_NAME, auditDto.getServiceName())
                .append(EVENT, auditDto.getEvent())
                .append(DATE, auditDto.getDate());
    }

    private Date extractDate(Document document) {
        return document.getDate(DATE);
    }
}
