package com.fit_goal.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MongoTestHelper {

    @NonNull
    private final MongoCollection<Document> collection;

    public MongoTestHelper(@NotNull MongoClient mongo, @NotNull String database, @NotNull String collection_name) {
        this.collection = mongo.getDatabase(database).getCollection(collection_name);
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

    public List<Document> allDocuments() {
        return collection
                .find()
                .into(new ArrayList<>());
    }
}
