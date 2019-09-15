package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.usach.tbd.model.Emergency;
import com.usach.tbd.model.Characteristic;
import com.usach.tbd.model.User;
import com.usach.tbd.repository.EmergencyRepository;
import com.usach.tbd.repository.CharacteristicRepository;
import com.usach.tbd.repository.UserRepository;
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
    private int numberRecords;

    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;

    public void emergencySeeder(int numberRecords){

        Emergency emergency;
        User u;
        Characteristic characteristic;

        for(int i = 0; i < numberRecords;i++) {


            emergency = new Emergency();
            emergency.setTitle(faker.job().title());
            emergency.setDescription(faker.lorem().paragraph(3));
            emergency.setType(faker.job().field());
            emergency.setLocation(faker.address().fullAddress());
            emergency.setStatus(faker.bool().bool());

            u =  new User();
            Long qty = userRepository.count();
            u = userRepository.findById((faker.number().numberBetween(1,qty))).get();
            emergency.setUser(u);


            //Many to many relation
            Set<Characteristic> characteristicSet = new HashSet<>();
            //emergency.setCharacteristics(characteristicSet);

            qty = characteristicRepository.count();
            for(int n=0;n<2;n++){
                characteristic = characteristicRepository.findById((faker.number().numberBetween(1,qty))).get();
                characteristicSet.add(characteristic);
            }
            //emergency.setCharacteristics(characteristicSet);

            emergencyRepository.save(emergency);

        }


    }
}
