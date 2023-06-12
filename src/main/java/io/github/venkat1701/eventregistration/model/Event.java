package io.github.venkat1701.eventregistration.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection="events")
@Getter
@Setter
@AllArgsConstructor
public class Event {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private List<Participant> winnersList;

}
