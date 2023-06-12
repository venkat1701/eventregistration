package io.github.venkat1701.eventregistration.repository;

import io.github.venkat1701.eventregistration.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {

    Event findEventById(String id);
    List<Event> findAllById(String id);
    List<Event> findEventByName(String name);

}
