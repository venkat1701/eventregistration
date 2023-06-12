package io.github.venkat1701.eventregistration.controller;


import io.github.venkat1701.eventregistration.model.Event;
import io.github.venkat1701.eventregistration.model.Participant;
import io.github.venkat1701.eventregistration.model.School;
import io.github.venkat1701.eventregistration.repository.EventRepository;
import io.github.venkat1701.eventregistration.repository.ParticipantRepository;
import io.github.venkat1701.eventregistration.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Controller for reaching out to the school endpoints.
 */

@RestController
@CrossOrigin("https://eventregistration2k23.netlify.app/")
public class SchoolController {



    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventRepository eventRepository;


    /**
     * Loads the list of all the schools.
     * @return List of Schools.
     */
    @GetMapping("/schools/all")
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    /**
     * Returns the particular school by ID.
     * @param id School ID.
     * @return  Returns the School with the mentioned ID.
     */
    @GetMapping("/schools/{id}")
    public School getSchoolByID(@PathVariable String id) {
        return schoolRepository.findSchoolById(id);
    }

    /**
     * Returns the List of Participants with the specific school ID.
     * @param id School Unique ID
     * @return List of Participants of a specific school.
     */
    @GetMapping("/schools/{id}/all")
    public List<Participant> getSchoolParticipantByID(@PathVariable String id){
        return schoolRepository.findSchoolById(id).getParticipants();
    }

    /**
     * Saves a school into the database.
     * @param school School Details.
     */
    @PostMapping("/school")
    public void saveSchool(@RequestBody School school){
        System.out.println(school);
        participantRepository.saveAll(school.getParticipants());
        eventRepository.saveAll(school.getEvents());
        schoolRepository.save(school);
    }

    /**
     * Bulk Entry of Schools.
     * @param schools School details in bulk.
     */
    @PostMapping("/school/massEntry")
    public void saveMass(@RequestBody List<School> schools) {
        schoolRepository.saveAll(schools);
        for(School school : schools) {
            participantRepository.saveAll(school.getParticipants());
            eventRepository.saveAll(school.getEvents());
        }
    }

    @PostMapping("/school/{id}/postParticipant")
    public void saveParticipant(@PathVariable String id, @RequestBody Participant participant) {
        School school = schoolRepository.findSchoolById(id);
        var newPart = school.getParticipants();
        var newEven = school.getEvents();
        newPart.add(participant);
        Event event = new Event(String.format("E%02d",(newEven.size()+1)),participant.getParticipantEvent(), new ArrayList<>());
        newEven.add(event);
        school.setEvents(newEven);
        school.setParticipants(newPart);
        participantRepository.save(participant);
        saveSchool(school);
    }


    /**
     * Updates the participant.
     * @param newParticipant School New Participant Details.
     */
    @PutMapping("/school/{oldStudentId}/update")
    public void updateParticipantByID( @PathVariable String oldStudentId, @RequestBody Participant newParticipant) {
        Participant participant = participantRepository.findAll().stream().filter(participant1 -> participant1.getId().equals(oldStudentId)).toList().get(0);
        participantRepository.deleteById(participant.getId());
        School oldSchool = schoolRepository.findAll().stream().filter(school -> school.getSchoolName().equals(participant.getParticipantSchool())).toList().get(0);
        List<Participant> oldList = oldSchool.getParticipants();
        if(participant != null) {
            oldList.remove(participant);
            oldList.add(newParticipant);
            oldSchool.setParticipants(oldList);
            schoolRepository.save(oldSchool);
            participantRepository.delete(participant);
            participantRepository.save(newParticipant);
            System.out.println("Updated");
        }
    }

    /**
     * Deletes all the schools.
     * @return Message of success of deletion of schools.
     */
    @DeleteMapping("/schools/deleteAll")
    public String deleteSchools(){
        schoolRepository.deleteAll();
        eventRepository.deleteAll();
        participantRepository.deleteAll();
        return "Done";
    }

    /**
     * Deletes a specific participant.
     * @param id School Unique ID
     * @param student_name School Participant Name.
     * @return Message of Success of deletion of participant.
     */
    @DeleteMapping("/schools/{id}/{student_name}")
    public String deleteParticipant(@PathVariable String id, @PathVariable String student_name) {
        Participant p = schoolRepository.findSchoolById(id).getParticipants().stream().filter(participant -> participant.getParticipantName().equals(student_name)).toList().get(0);
        participantRepository.delete(p);
        School school = schoolRepository.findSchoolById(id);
        schoolRepository.deleteById(id);
        List<Participant> list = school.getParticipants();
        list.remove(p);
        school.setParticipants(list);
        schoolRepository.save(school);

        return "Deleted Participant";
    }


    @GetMapping("/login")
    public String success() {
        return "success";
    }
}
