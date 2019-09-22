package com.usach.tbd.controller;


import com.usach.tbd.model.Characteristic;
import com.usach.tbd.model.Volunteer;
import com.usach.tbd.repository.CharacteristicRepository;
import com.usach.tbd.repository.VolunteerRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/volunteers")
public class VolunteerController {
    private VolunteerRepository volunteerRepository;
    private final CharacteristicController characteristicController;

    @Autowired
    public VolunteerController(VolunteerRepository volunteerRepository, CharacteristicController characteristicController) {
        this.volunteerRepository = volunteerRepository;
        this.characteristicController = characteristicController;
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

    @RequestMapping(value = "/{volunteerId}/add_characteristic/{score}", method = RequestMethod.POST)
    @ResponseBody
    public Volunteer addCharacteristic(@PathVariable Long volunteerId, @RequestBody Characteristic characteristic, @PathVariable int score){
        Optional o_volunteer = volunteerRepository.findById(volunteerId);
        Volunteer volunteer;
        if (o_volunteer.isPresent()){
            volunteer = (Volunteer)o_volunteer.get();
        } else {
            return null;
        }
        characteristic = characteristicController.createCharacteristic(characteristic);
        volunteer.addCharacteristic(characteristic, score);
        volunteerRepository.save(volunteer);
        System.out.println(characteristic.toString());
        return volunteer;
    }

    @RequestMapping(value = "/{volunteerId}/add_characteristics", method = RequestMethod.POST)
    @ResponseBody
    public Volunteer addCharacteristics(@PathVariable Long volunteerId, @RequestBody String characteristics){
        Optional o_volunteer = volunteerRepository.findById(volunteerId);
        Volunteer volunteer;
        if (o_volunteer.isPresent()){
            volunteer = (Volunteer)o_volunteer.get();
        } else {
            return null;
        }

        characteristics = characteristics.replaceAll("'", "\"");
        System.out.println(characteristics);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try{
           jsonArray = (JSONArray) parser.parse(characteristics);
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
        Characteristic characteristic;

        for (Object o: jsonArray) {
            JSONObject jo = (JSONObject) o;
            System.out.println("NAME: " + jo.get("name") + " - SCORE: " + jo.get("score"));
            Characteristic c = new Characteristic();
            c.setName((String)jo.get("name"));
            characteristic = characteristicController.createCharacteristic(c);
            volunteer.addCharacteristic(characteristic, Integer.parseInt(Long.toString((Long)jo.get("score"))));
            volunteerRepository.save(volunteer);
        }
        return volunteer;
    }
}