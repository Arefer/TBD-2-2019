package com.usach.tbd.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date postedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;



    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
