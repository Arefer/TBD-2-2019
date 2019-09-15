package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.usach.tbd.model.Characteristic;
import com.usach.tbd.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CharacteristicSeeder {

    @Autowired
    private CharacteristicRepository characteristicRepository;

    private Faker faker = new Faker(new Locale("es"));

    public void characteristicSeeder(int numberRecord){

        Characteristic characteristic;

        for(int i =0; i<numberRecord;i++){

            characteristic = new Characteristic();

            characteristic.setName(faker.job().keySkills());
            characteristic.setDescription(faker.lorem().paragraph(2));



            characteristicRepository.save(characteristic);
        }
    }
}
