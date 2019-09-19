package com.usach.tbd.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name="CharacteristicVolunteer")
@Table(name = "characteristic_volunteer")
public class CharacteristicVolunteer implements Serializable {

    @EmbeddedId
    private CharacteristicVolunteerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("volunteer_id")
    //@JsonBackReference
    private Volunteer volunteer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characteristic_id")
    private Characteristic characteristic;

    private int score;

    public CharacteristicVolunteer() { }

    public CharacteristicVolunteer(Volunteer volunteer, Characteristic characteristic, int score){
        this.volunteer = volunteer;
        this.characteristic = characteristic;
        this.score = score;
    }


    public CharacteristicVolunteerId getId() {
        return id;
    }

    public void setId(CharacteristicVolunteerId id) {
        this.id = id;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacteristicVolunteer)) return false;
        CharacteristicVolunteer that = (CharacteristicVolunteer) o;
        return getScore() == that.getScore() &&
                id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getScore());
    }

    @Override
    public String toString() {
        return "CharacteristicVolunteer{" +
                "id=" + id +
                ", score=" + score +
                '}';
    }
}
