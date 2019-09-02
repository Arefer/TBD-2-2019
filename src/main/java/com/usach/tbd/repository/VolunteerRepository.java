package com.usach.tbd.repository;

import com.usach.tbd.model.Volunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {

}
