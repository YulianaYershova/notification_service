package com.fit_goal.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bson.Document;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class DocumentUtils {
    List<Document> parseDocuments(@NonNull String resourceName) {
        try {
            String docJson = IOUtils.resourceToString(resourceName, StandardCharsets.UTF_8);

            Object content = Document.parse("{\"json\":" + docJson + "}").get("json");
            return content instanceof List
                    ? (List<Document>) content
                    : Collections.singletonList((Document) content);
        } catch (IOException ex) {
            throw new RuntimeException("Can't handle resource: " + resourceName, ex);
        }
    }
}
