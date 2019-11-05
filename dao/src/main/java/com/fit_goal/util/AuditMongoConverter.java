package com.fit_goal.util;

import com.fit_goal.AuditDtoFields;
import com.fit_goal.domain.AuditDto;
import lombok.experimental.UtilityClass;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fit_goal.AuditDtoFields.*;

@UtilityClass
public class AuditMongoConverter {

    public AuditDto documentToAuditDto(Document document) {
        return AuditDto.builder()
                .id(document.getObjectId(ID).toString())
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(document.getString(DATE), formatter);
        //return document.getDate(DATE).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
