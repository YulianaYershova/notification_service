package com.fit_goal.impl;

import com.fit_goal.EventDao;
import com.fit_goal.domain.EventDto;
import com.fit_goal.util.EventMongoConverter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import lombok.NonNull;
import org.bson.types.ObjectId;

import javax.inject.Inject;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.*;
import static com.fit_goal.EventDtoFields.*;

public class EventDaoImpl implements EventDao {

    private static final String COLLECTION_NAME = "event";
    private static final String DATABASE = "notification_db";


    private final MongoCollection<Document> collection;

    @Inject
    public EventDaoImpl(@NonNull MongoClient mongo) {
        this.collection = mongo.getDatabase(DATABASE).getCollection(COLLECTION_NAME);
    }

    @Override
    public void create(EventDto eventDto) {
        collection.insertOne(EventMongoConverter.eventDtoToDocument(eventDto));
    }

    @Override
    public Optional<EventDto> findById(ObjectId id) {
        Document document = collection.find(eq(ID, id)).first();

        return Optional.ofNullable(document)
                .map(EventMongoConverter::documentToEventDto);
    }

    @Override
    public List<EventDto> findAll() {
        MongoCursor<Document> mongoCursor = collection.find().iterator();
        List<EventDto> documents = new ArrayList<>();

        while (mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            documents.add(EventMongoConverter.documentToEventDto(document));
        }
        return documents;
    }

    @Override
    public EventDto update(EventDto eventDto) {
        collection.updateOne(new Document(ID, eventDto.getId()),
                new Document("$set", EventMongoConverter.eventDtoToDocument(eventDto)));
        return eventDto;
    }

    @Override
    public void delete(EventDto eventDto) {
        collection.deleteOne(eq(ID, eventDto.getId()));
    }


}
