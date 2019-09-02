package com.usach.tbd.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String type;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;



    public Long getId() {
        return id;
    }

}
