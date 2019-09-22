package com.usach.tbd.controller;

import com.usach.tbd.model.Emergency;
import com.usach.tbd.model.Task;
import com.usach.tbd.repository.EmergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/emergencies")
public class EmergencyController {
    private EmergencyRepository emergencyRepository;

    @Autowired
    public EmergencyController(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Emergency> listEmergencies() {
        return (List<Emergency>) this.emergencyRepository.findAll();
    }

    @RequestMapping(value="/post", method = RequestMethod.POST)
    @ResponseBody
    public Emergency createEmergency(@RequestBody Emergency emergency) {
        return this.emergencyRepository.save(emergency);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Emergency getEmergency(@PathVariable Long id) {
        return this.emergencyRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Emergency updateEmergency(@PathVariable String id, @RequestBody Emergency emergency) {
        if (!emergency.getId().equals(id)){
            return null;
        }
        return this.emergencyRepository.save(emergency);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Emergency deleteEmergency(@PathVariable Long id) {
        Optional<Emergency> emergency = this.emergencyRepository.findById(id);
        if (emergency.isPresent()) {
            this.emergencyRepository.delete(emergency.get());
        }
        return null;
    }

    @RequestMapping(value= "/{id}/tasks", method = RequestMethod.GET)
    @ResponseBody
    public List<Task> emergencyTasks(@PathVariable Long id){
        Optional oEmergency = emergencyRepository.findById(id);
        if (oEmergency.isPresent()){
            Emergency emergency = (Emergency)oEmergency.get();
            return new ArrayList<>(emergency.getTasks());
        } else {
            return null;
        }
    }
}
