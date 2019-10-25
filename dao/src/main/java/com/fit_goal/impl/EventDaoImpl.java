package com.fit_goal.impl;

import com.fit_goal.EventMapper;
import com.fit_goal.domain.EventDto;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class EventDaoImpl {

    /**
     * The collection of Events
     */
    private final MongoCollection<Document> collection;

    /**
     * Constructor.
     *
     * @param collection the collection of events.
     */
    public EventDaoImpl(final MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void insertOne(Document document) {
        collection.insertOne(document);
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
