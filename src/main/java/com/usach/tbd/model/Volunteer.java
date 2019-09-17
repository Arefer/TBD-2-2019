package com.usach.tbd.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "volunteers")

public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String userName;

    private String password;

    private String rut;

    private char sex;

    private String phone;

    private String email;

    private String address;

    private float latitude;

    private float longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date postedAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @ManyToMany(mappedBy = "volunteers", fetch=FetchType.EAGER)
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "volunteers", fetch=FetchType.EAGER)
    private Set<Emergency> emergencies;

    @ManyToMany(cascade = CascadeType.MERGE,
                fetch=FetchType.EAGER)
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
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //Last Name
    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    //User Name
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

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

    //Sex
    public char getSex() { return sex; }

    public void setSex(char sex) { this.sex = sex; }

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

    //Address
    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    //Latitude
    public float getLatitude() { return latitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    //Longitude
    public float getLongitude() { return longitude; }

    public void setLongitude(float longitude) { this.longitude = longitude; }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastName(), getUserName(), getPassword(), getRut(), getSex(), getPhone(), getEmail(), getAddress(), getLatitude(), getLongitude(), postedAt, lastUpdatedAt, tasks, emergencies, getCharacteristics());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Volunteer)) return false;
        Volunteer volunteer = (Volunteer) o;
        return getSex() == volunteer.getSex() &&
                Float.compare(volunteer.getLatitude(), getLatitude()) == 0 &&
                Float.compare(volunteer.getLongitude(), getLongitude()) == 0 &&
                getId().equals(volunteer.getId()) &&
                getName().equals(volunteer.getName()) &&
                getLastName().equals(volunteer.getLastName()) &&
                getUserName().equals(volunteer.getUserName()) &&
                getPassword().equals(volunteer.getPassword()) &&
                getRut().equals(volunteer.getRut()) &&
                getPhone().equals(volunteer.getPhone()) &&
                getEmail().equals(volunteer.getEmail()) &&
                getAddress().equals(volunteer.getAddress()) &&
                postedAt.equals(volunteer.postedAt) &&
                lastUpdatedAt.equals(volunteer.lastUpdatedAt) &&
                tasks.equals(volunteer.tasks) &&
                emergencies.equals(volunteer.emergencies) &&
                getCharacteristics().equals(volunteer.getCharacteristics());
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", rut='" + rut + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", postedAt=" + postedAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", tasks=" + tasks +
                ", emergencies=" + emergencies +
                ", characteristics=" + characteristics +
                '}';
    }
}


