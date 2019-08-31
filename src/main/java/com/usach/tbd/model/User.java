package com.usach.tbd.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String rut;
    @NotNull
    private String telephone;
    @NotNull
    private String email;

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
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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



