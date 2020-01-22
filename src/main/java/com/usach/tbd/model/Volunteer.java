package com.usach.tbd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private String sex;

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

    /*@ManyToMany(mappedBy = "volunteers", fetch=FetchType.LAZY)
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "volunteers", fetch=FetchType.LAZY)
    private Set<Emergency> emergencies;*/

    //@OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL )
    //@JsonIgnoreProperties("volunteer")
    //private Set<CharacteristicVolunteer> characteristics = new HashSet<>();

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
    /*public Set<CharacteristicVolunteer> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<CharacteristicVolunteer> characteristics) {
        this.characteristics = characteristics;
    }*/

    public void addCharacteristic(Characteristic characteristic, int score){
        CharacteristicVolunteerId cvid = new CharacteristicVolunteerId(characteristic.getId(), this.id);
        CharacteristicVolunteer cv = new CharacteristicVolunteer(this, characteristic, score);
        cv.setId(cvid);
        //characteristics.add(cv);
    }
    //Latitude
    public float getLatitude() { return latitude; }

    public void setLatitude(float latitude) { this.latitude = latitude; }

    //Longitude
    public float getLongitude() { return longitude; }

    public void setLongitude(float longitude) { this.longitude = longitude; }

}


