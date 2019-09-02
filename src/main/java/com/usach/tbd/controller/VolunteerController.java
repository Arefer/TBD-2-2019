package com.usach.tbd.controller;


import com.usach.tbd.model.Volunteer;
import com.usach.tbd.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/volunteers")
public class VolunteerController {
    private VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerController(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Volunteer> listVolunteers() {
        return (List<Volunteer>) this.volunteerRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
        return this.volunteerRepository.save(volunteer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Volunteer getVolunteer(@PathVariable Long id) {
        return this.volunteerRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Volunteer updateVolunteer(@PathVariable String id, @RequestBody Volunteer volunteer) {
        if (!volunteer.getId().equals(id)) {
            return null;
        }
        return this.volunteerRepository.save(volunteer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Volunteer deleteVolunteer(@PathVariable Long id) {
        Optional<Volunteer> volunteer = this.volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            this.volunteerRepository.delete(volunteer.get());
        }
        return null;
    }
}