package com.usach.tbd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String type;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emergency_id", nullable = false)
    @JsonIgnore
    private Emergency emergency;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "task_volunteer", joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"))
    private Set<Volunteer> volunteers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "characteristic_task", joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id", referencedColumnName = "id"))
    private Set<Characteristic> characteristics;


    public Long getId() {
        return id;
    }

}
