package com.usach.tbd.controller;


import com.usach.tbd.model.Emergency;
import com.usach.tbd.model.Task;
import com.usach.tbd.model.Volunteer;
import com.usach.tbd.repository.EmergencyRepository;
import com.usach.tbd.repository.TaskRepository;
import com.usach.tbd.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/tasks")
public class TaskController {
    private TaskRepository taskRepository;
    private VolunteerRepository volunteerRepository;
    private EmergencyRepository emergencyRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, VolunteerRepository volunteerRepository, EmergencyRepository emergencyRepository) {
        this.taskRepository = taskRepository;
        this.volunteerRepository = volunteerRepository;
        this.emergencyRepository = emergencyRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Task> listTaks() {
        return (List<Task>) this.taskRepository.findAll();
    }

    @RequestMapping(value="/post/{emergencyId}", method = RequestMethod.POST)
    @ResponseBody
    public Task createTask(@PathVariable Long emergencyId, @RequestBody Task task) {
            task.setEmergency(emergencyRepository.findById(emergencyId).get());
            return this.taskRepository.save(task);
    }
    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task getTask(@PathVariable Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Task updateTask(@PathVariable String id, @RequestBody Task task) {
        if (!task.getId().equals(id)) {
            return null;
        }
        return this.taskRepository.save(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Task deleteTask(@PathVariable Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        if (task.isPresent()) {
            this.taskRepository.delete(task.get());
        }
        return null;
    }

    @RequestMapping(value = "/assignVolunteer/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Task assignVolunteer(@PathVariable Long id, @RequestParam Long idVolunteer) {
        Task task = this.taskRepository.findById(id).get();
        task.getVolunteers().add(volunteerRepository.findById(idVolunteer).get());
        return this.taskRepository.save(task);
    }

    @RequestMapping(value= "/{id}/volunteer", method = RequestMethod.GET)
    @ResponseBody
    public List<Volunteer> volunteerTasks(@PathVariable Long id){
        Optional oTask = taskRepository.findById(id);
        if (oTask.isPresent()){
            Task task = (Task)oTask.get();
            return new ArrayList<>(task.getVolunteers());
        } else {
            return null;
        }
    }
}