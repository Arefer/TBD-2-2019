package com.usach.tbd.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "volunteers")

public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String rut;

    private String phone;

    private String email;

    private String address;

    @ManyToMany(mappedBy = "volunteers")
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "volunteers")
    private Set<Emergency> emergencies;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "characteristic_volunteer", joinColumns = @JoinColumn(name = "volunteer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id", referencedColumnName = "id"))
    private Set<Characteristic> characteristics;



    public Volunteer() {
    }

    public Volunteer(Long id, String name, String password) {

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

    // characteristics
    public Set<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristic> characteristics) {
        this.characteristics = characteristics;
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

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, rut, phone, email, characteristics);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final com.usach.tbd.model.Volunteer other = (com.usach.tbd.model.Volunteer) obj;
        if (this.password != other.password) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", rut='" + rut + '\'' +
                ", telephone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", characteristics=" + characteristics +
                '}';
    }

}


