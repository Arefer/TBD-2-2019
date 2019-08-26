package com.usach.tbd.model;
import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private Long id;


    public Task(Long id) {
        this.id = id;
    }
}
