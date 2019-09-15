package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.usach.tbd.model.Volunteer;
import com.usach.tbd.repository.CharacteristicRepository;
import com.usach.tbd.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Locale;

@Component
public class VolunteerSeeder {
    
    private Faker faker = new Faker(new Locale("es"));

    @Autowired
    private CharacteristicRepository characteristicRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;

    public void volunteerSeeder(int numberRecords){

        Volunteer volunteer;

        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("es"), new RandomService());


        Long characteristicQty = characteristicRepository.count();

        for(int i=0;i < numberRecords;i++){


            volunteer = new Volunteer();

            volunteer.setName(faker.name().firstName());
            volunteer.setEmail(fakeValuesService.bothify("????##@gmail.com"));
            volunteer.setPassword(fakeValuesService.regexify("[a-z1-9]{10}"));
            volunteer.setPhone(faker.phoneNumber().cellPhone());
            volunteer.setRut(fakeValuesService.numerify(fakeValuesService.regexify("[1-2]{1}") + "#.###.###-" + fakeValuesService.regexify("[1-9k]{1}")));
            volunteer.setAddress(faker.address().fullAddress());

            //Many to many
            volunteer.setCharacteristics(new HashSet<>());

            for(int n=0;n<3;n++){
                volunteer.getCharacteristics().add(characteristicRepository.findById(faker.number().numberBetween(1,characteristicQty)).get());
            }

            volunteerRepository.save(volunteer);
        }

    }
}
