package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.usach.tbd.model.Emergency;
import com.usach.tbd.model.Characteristic;
import com.usach.tbd.model.User;
import com.usach.tbd.model.Volunteer;
import com.usach.tbd.repository.EmergencyRepository;
import com.usach.tbd.repository.CharacteristicRepository;
import com.usach.tbd.repository.UserRepository;
import com.usach.tbd.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
public class EmergencySeeder {

    private Faker faker = new Faker(new Locale("es"));

    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;

    public void emergencySeeder(int numberRecords){

        Emergency emergency;
        User u;

        Long userQty = userRepository.count();
        Long characteristicQty = characteristicRepository.count();
        Long volunteerQty = volunteerRepository.count();

        for(int i = 0; i < numberRecords;i++) {


            emergency = new Emergency();
            emergency.setTitle(faker.job().title());
            emergency.setDescription(faker.lorem().paragraph(2));
            emergency.setType(faker.job().field());
            emergency.setLocation(faker.address().fullAddress());
            emergency.setStatus(faker.bool().bool());

            u =  new User();
            u = userRepository.findById((faker.number().numberBetween(1,userQty))).get();
            emergency.setUser(u);


            //Many to many relation

            emergency.setCharacteristics(new HashSet<>());
            emergency.setVolunteers(new HashSet<>());

            for(int n=0;n<4;n++){
                emergency.getCharacteristics().add(characteristicRepository.findById(faker.number().numberBetween(1,characteristicQty)).get());
            }

            for(int n=0;n<1;n++) {
                emergency.getVolunteers().add(volunteerRepository.findById(faker.number().numberBetween(1, volunteerQty)).get());
            }


            emergencyRepository.save(emergency);

        }


    }
}
