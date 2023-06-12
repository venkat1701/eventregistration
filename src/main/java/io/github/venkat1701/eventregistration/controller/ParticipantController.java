package io.github.venkat1701.eventregistration.controller;


import io.github.venkat1701.eventregistration.model.Participant;
import io.github.venkat1701.eventregistration.repository.EventRepository;
import io.github.venkat1701.eventregistration.repository.ParticipantRepository;
import io.github.venkat1701.eventregistration.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("https://eventregistration2k23.netlify.app/")
public class ParticipantController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping("/participants/all")
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @GetMapping("/participants/{id}")
    public Participant getParticipantById(@PathVariable String id) {
        return participantRepository.findParticipantById(id);
    }

}
