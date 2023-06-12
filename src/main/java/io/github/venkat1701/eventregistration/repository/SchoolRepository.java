package io.github.venkat1701.eventregistration.repository;

import io.github.venkat1701.eventregistration.model.Event;
import io.github.venkat1701.eventregistration.model.Participant;
import io.github.venkat1701.eventregistration.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends MongoRepository<School, String> {

    School findSchoolById(String id);
    List<School> findAllById(String id);

}
