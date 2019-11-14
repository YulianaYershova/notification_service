package com.fitgoal.dao.impl.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.fitgoal.dao.util.AuditDtoFields.DATE;

@UtilityClass
class DocumentUtils {

    private static final  SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    List<Document> parseDocuments(@NonNull String resourceName) {
        try {
            String docJson = IOUtils.resourceToString(resourceName, StandardCharsets.UTF_8);

            Object content = Document.parse("{\"json\":" + docJson + "}").get("json");
            List<Document> documents = (List<Document>) content;
            return documents.stream().map(DocumentUtils::convertDateFromString).collect(Collectors.toList());
        } catch (IOException  ex) {
            throw new RuntimeException("Can't handle resource: " + resourceName, ex);
        }
    }

    private Document convertDateFromString(Document document) {
        LocalDateTime date = null;
        try {
            date = SIMPLE_DATE_FORMAT
                    .parse(document.getString(DATE))
                    .toInstant()
                    .atZone(ZoneOffset.UTC)
                    .toLocalDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        document.put(DATE, date);
        return document;
    }
}
