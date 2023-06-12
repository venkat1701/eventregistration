package io.github.venkat1701.eventregistration.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter @Setter
@Document(collection="participants")
public class Participant {

    @Id
    private String id;

    @Field
    private String participantName;
    @Field
    private String participantSchool;
    @Field
    private String participantEvent;
    @Field
    private String eventId;
}
