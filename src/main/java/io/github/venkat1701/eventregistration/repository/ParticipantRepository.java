package io.github.venkat1701.eventregistration.repository;

import io.github.venkat1701.eventregistration.model.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> {

    Participant findParticipantById(String id);
}
