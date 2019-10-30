package com.fit_goal;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface CrudOperations<T> {

    void create(T value);

    Optional<T> findById(ObjectId id);

    List<T> findAll();

    T update(T value);

    void delete(T value);
}
