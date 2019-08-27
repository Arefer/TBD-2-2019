package com.usach.tbd.repository;


import com.usach.tbd.model.Emergency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRepository extends CrudRepository<Emergency, Long> {

}
