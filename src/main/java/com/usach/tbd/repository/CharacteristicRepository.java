package com.usach.tbd.repository;


import com.usach.tbd.model.Characteristic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicRepository extends CrudRepository<Characteristic, Long> {

}
