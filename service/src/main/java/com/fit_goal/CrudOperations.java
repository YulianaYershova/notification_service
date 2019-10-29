package com.fit_goal;

import com.fit_goal.domain.EventDto;
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
