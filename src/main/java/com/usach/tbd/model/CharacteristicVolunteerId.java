package com.usach.tbd.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class CharacteristicVolunteerId implements Serializable {
    @Column(name = "characteristic_id")
    private Long characteristicId;

    @Column(name = "volunteer_id")
    private Long volunteerId;

    public CharacteristicVolunteerId() { }

    public CharacteristicVolunteerId(Long characteristicId, Long volunteerId) {
        this.characteristicId = characteristicId;
        this.volunteerId = volunteerId;
    }

    public Long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(Long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CharacteristicVolunteerId)) return false;
        CharacteristicVolunteerId that = (CharacteristicVolunteerId) o;
        return getCharacteristicId().equals(that.getCharacteristicId()) &&
                getVolunteerId().equals(that.getVolunteerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacteristicId(), getVolunteerId());
    }
}
