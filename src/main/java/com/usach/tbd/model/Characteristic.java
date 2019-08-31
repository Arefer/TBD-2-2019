package com.usach.tbd.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "characteristics")

public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;


    public Characteristic() {
    }

    public Characteristic(Long id, String name, int population) {

        this.id = id;
        this.description = name;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.description);
        return hash;
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
        final Characteristic other = (Characteristic) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Characteristic{");
        sb.append("id=").append(id);
        sb.append(", name='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}