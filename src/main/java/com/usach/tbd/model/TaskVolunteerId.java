package com.usach.tbd.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskVolunteerId implements Serializable {
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "volunteer_id")
    private Long volunteerId;

    public TaskVolunteerId(Long taskId, Long volunteerId) {
        this.taskId = taskId;
        this.volunteerId = volunteerId;
    }

    public TaskVolunteerId() { }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskVolunteerId)) return false;
        TaskVolunteerId that = (TaskVolunteerId) o;
        return Objects.equals(getTaskId(), that.getTaskId()) &&
                Objects.equals(getVolunteerId(), that.getVolunteerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getVolunteerId());
    }
}
