package com.fitgoal.dao.impl.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Consumer;

import static com.fitgoal.dao.util.AuditDtoFields.DATE;

@UtilityClass
class DocumentUtils {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    List<Document> parseDocuments(@NonNull String resourceName) {
        try {
            String docJson = IOUtils.resourceToString(resourceName, StandardCharsets.UTF_8);
            Object content = Document.parse("{\"json\":" + docJson + "}").get("json");
            List<Document> documents = (List<Document>) content;
            documents.forEach(fixDate());
            return documents;
        } catch (IOException ex) {
            throw new RuntimeException("Can't handle resource: " + resourceName, ex);
        }
    }

    @NotNull
    private Consumer<Document> fixDate() {
        return document -> document
                .put(DATE, convertDate(document.getString(DATE)));
    }

    private LocalDateTime convertDate(String date) {
        LocalDateTime localDateTime = null;
        try {
            localDateTime = SIMPLE_DATE_FORMAT
                    .parse(date)
                    .toInstant()
                    .atZone(ZoneOffset.UTC)
                    .toLocalDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return localDateTime;
    }
}
