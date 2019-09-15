package com.usach.tbd.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String rut;
    @NotNull
    private String phone;
    @NotNull
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date postedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Emergency> emergencies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;




    public User() {
    }

    public User(Long id, String name, String password) {

        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //Rut
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    //Telephone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Emergency
    public Set<Emergency> getEmergencies() {
        return emergencies;
    }

    public void setEmergencies(Set<Emergency> emergencies) {
        this.emergencies = emergencies;
    }
}



