package com.fit_goal.impl;

import com.fit_goal.AuditDao;
import com.fit_goal.domain.AuditDto;
import com.fit_goal.util.AuditMongoConverter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import lombok.NonNull;
import org.bson.types.ObjectId;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.fit_goal.AuditDtoFields.*;
import static com.mongodb.client.model.Filters.eq;

public class AuditDaoImpl implements AuditDao {

    private static final String DATABASE = "notification_db";
    private static final String COLLECTION_NAME = "audit";

    private final MongoCollection<Document> collection;

    @Inject
    public AuditDaoImpl(@NonNull MongoClient mongo) {
        this.collection = mongo.getDatabase(DATABASE).getCollection(COLLECTION_NAME);
    }

    @Override
    public void create(AuditDto auditDto) {
        collection.insertOne(AuditMongoConverter.auditDtoToDocument(auditDto));
    }

    @Override
    public Optional<AuditDto> findById(String id) {
        Document document = collection.find(eq(ID, new ObjectId(id))).first();

        return Optional.ofNullable(document)
                .map(AuditMongoConverter::documentToAuditDto);
    }

    @Override
    public List<AuditDto> findAll() {
        Spliterator<Document> mongoCursor = collection.find().spliterator();
        return StreamSupport.stream(mongoCursor, false) .map(AuditMongoConverter::documentToAuditDto) .collect(Collectors.toList());
    }

    @Override
    public AuditDto update(AuditDto auditDto) {
        Document id = new Document(ID, auditDto.getId());
        Document updatedDocument =  new Document("$set", AuditMongoConverter.auditDtoToDocument(auditDto));
        collection.updateOne(id, updatedDocument);
        return auditDto;
    }

    @Override
    public void delete(AuditDto auditDto) {
        collection.deleteOne(eq(ID, auditDto.getId()));
    }
}
