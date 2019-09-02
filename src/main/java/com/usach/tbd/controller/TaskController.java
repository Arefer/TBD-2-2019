package com.usach.tbd.controller;


import com.usach.tbd.model.Task;
import com.usach.tbd.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Task> listTaks() {
        return (List<Task>) this.taskRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Task createTask(@RequestBody Task task) {
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
}