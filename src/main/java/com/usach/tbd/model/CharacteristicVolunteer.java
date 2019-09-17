package com.usach.tbd.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "characteristic_volunteer")
public class CharacteristicVolunteer {
    @EmbeddedId
    private CharacteristicVolunteerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characteristicId")
    private Characteristic characteristic;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("volunteerId")
    private Volunteer volunteer;

    private int score;

    public CharacteristicVolunteer() { }

    public CharacteristicVolunteer(Characteristic c, Volunteer v){
        this.characteristic = c;
        this.volunteer = v;
        this.id = new CharacteristicVolunteerId(c.getId(), v.getId());
    }

    public CharacteristicVolunteerId getId() {
        return id;
    }

    public void setId(CharacteristicVolunteerId id) {
        this.id = id;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
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
                getId().equals(that.getId()) &&
                getCharacteristic().equals(that.getCharacteristic()) &&
                getVolunteer().equals(that.getVolunteer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCharacteristic(), getVolunteer(), getScore());
    }
}
