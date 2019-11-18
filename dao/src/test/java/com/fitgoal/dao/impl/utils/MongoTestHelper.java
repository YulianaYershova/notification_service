package com.fitgoal.dao.impl.utils;

import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.dao.util.AuditMongoConverter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MongoTestHelper {

    @NonNull
    private final MongoCollection<Document> collection;

    public MongoTestHelper(@NotNull MongoClient mongo, @NotNull String database, @NotNull String collectionName) {
        this.collection = mongo
                .getDatabase(database)
                .getCollection(collectionName);
    }

    public void deleteAll() {
        collection.deleteMany(new Document());
    }

    public void importDocuments(@NonNull String docsJsonResource) {
        insertDocuments(DocumentUtils.parseDocuments(docsJsonResource));
    }

    private void insertDocuments(@NonNull List<Document> documents) {
        collection.insertMany(documents);
    }

    public List<AuditDto> allData() {
        return collection
                .find()
                .into(new ArrayList<>())
                .stream()
                .map(AuditMongoConverter::documentToAuditDto)
                .collect(Collectors.toList());
    }
}
