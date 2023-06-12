package io.github.venkat1701.eventregistration.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection="schools")
@Getter
@Setter

public class School {

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.ASCENDING)
    private String schoolName;

    @DBRef
    private List<Participant> participants;

    @DBRef
    private List<Event> events;

    @
            Override
    public String toString() {
        return "School{" +
                "id='" + id + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", participants=" + participants +
                ", events=" + events +
                '}';
    }

}
