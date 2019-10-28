package com.fit_goal.impl;

import com.fit_goal.EventDao;
import com.fit_goal.EventMapper;
import com.fit_goal.domain.EventDto;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import lombok.NonNull;

import javax.inject.Inject;
import javax.inject.Named;

import javax.inject.Named;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class EventDaoImpl implements EventDao {

    private static final String COLLECTION_NAME = "event";
    private static final String DATABASE = "notification_db";


    /**
     * The collection of Events
     */
    private final MongoCollection<Document> collection;

    /**
     * Constructor.
     *
     * @param
     */
    @Inject
    public EventDaoImpl(@NonNull MongoClient mongo) {
        this.collection = mongo.getDatabase(DATABASE).getCollection(COLLECTION_NAME);
    }

    @Override
    public void create(EventDto eventDto) {
        final Document eventDocument = new Document("service_name", "some_service").append("event", "sending verification link").append("date", LocalDateTime.now());
        collection.insertOne(eventDocument);
    }

    public void insertMany(List<Document> documents) {
        collection.insertMany(documents);
    }

    public List<EventDto> find() {
        final MongoCursor<Document> documents = collection.find().iterator();
        final List<EventDto> donutsFind = new ArrayList<>();
        try {
            while (documents.hasNext()) {
                final Document document = documents.next();
                donutsFind.add(EventMapper.map(document));
            }
        } finally {
            documents.close();
        }
        return donutsFind;
    }

    public List<Document> findByKey(String key, String value) {
        return collection.find(eq(key, value)).into(new ArrayList<>());
    }

    public List<Document> findByCriteria(String key, int lessThanValue, int greaterThanValue, int sortOrder) {
        List<Document> documents = new ArrayList<>();
        FindIterable iterable = collection.find(and(lt(key, lessThanValue),
                gt(key, greaterThanValue))).sort(new Document(key, sortOrder));
        iterable.into(documents);
        return documents;
    }

    public void updateOneEvent(String key1, String key2, String key3, EventDto eventDto) {
        collection.updateOne(new Document(key1, eventDto.getId()),
                new Document("$set", new Document(key2, eventDto.getEvent()).append(key3, eventDto.getServiceName())));
    }


    public void deleteOne(String key, String value) {
        collection.deleteOne(eq(key, value));
    }
}
