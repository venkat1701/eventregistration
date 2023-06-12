package io.github.venkat1701.eventregistration.controller;

import io.github.venkat1701.eventregistration.model.Event;
import io.github.venkat1701.eventregistration.model.Participant;
import io.github.venkat1701.eventregistration.repository.EventRepository;
import io.github.venkat1701.eventregistration.repository.ParticipantRepository;
import io.github.venkat1701.eventregistration.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private EventRepository eventRepository;

    /**
     * List of Events.
     * @return List of Events.
     */
    @GetMapping("/events")
    public List<Event> events() {
        return eventRepository.findAll();
    }

    /**
     * List of Participants of a specific Event.
     * @param id Event ID.
     * @return List of Participants.
     */
    @GetMapping("/events/participants/{id}")
    public List<Participant> getEventParticipants(@PathVariable String id) {
        Event event = eventRepository.findEventById(id);
        return participantRepository.findAll()
                .stream()
                .filter(participant -> participant.getParticipantEvent().equals(event.getName()))
                .collect(Collectors.toList());
    }

    /**
     * List of Participants of a particular event filtered by Name.
     * @param id Event Unique ID.
     * @param student_name School Participant Name.
     * @return List of Participant.
     */
    @GetMapping("/events/{id}/{student_name}")
    public List<Participant> getEventParticipantsFilterName(@PathVariable String id, @PathVariable String student_name) {
        Event event = eventRepository.findAll().stream().filter(event1 -> event1.getName().equals(id)).collect(Collectors.toList()).get(0);
        return participantRepository.findAll()
                .stream()
                .filter(participant -> participant.getParticipantEvent().equals(event.getName()) && participant.getParticipantName().equals(student_name))
                .toList();
    }

    @PostMapping("/events/{id}/winners")
    public void setWinnersOfEvents(@RequestParam String id, @RequestBody List<Participant> participantList) {
        Event event = eventRepository.findEventById(id);
        event.setWinnersList(participantList);
        eventRepository.save(event);
    }

    /**
     * Deletes Event by ID.
     * @param id Event Unique ID.
     * @return
     */
    @DeleteMapping("/events/{id}")
    public String deleteEventById(@PathVariable String id) {
        eventRepository.deleteById(id);
        return "Deleted Event.";
    }


    @GetMapping("/events/{id}")
    public Event getEventById(@PathVariable String id) {
        return eventRepository.findEventById(id);
    }


 }
