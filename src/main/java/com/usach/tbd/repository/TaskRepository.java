package com.usach.tbd.repository;
import org.springframework.data.repository.CrudRepository;
import com.usach.tbd.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findByid(Long id);
}

