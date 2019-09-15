package com.usach.tbd;

import com.usach.tbd.seeder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBseeder {

    @Autowired
    private EmergencySeeder emergencySeeder;
    @Autowired
    private UserSeeder userSeeder;
    @Autowired
    private RoleSeeder roleSeeder;
    @Autowired
    private CharacteristicSeeder characteristicSeeder;
    @Autowired
    private TaskSeeder taskSeeder;
    @Autowired
    private VolunteerSeeder volunteerSeeder;

    //@PostConstruct
    public void seed(){

        characteristicSeeder.characteristicSeeder(5);
        roleSeeder.roleSeeder();
        userSeeder.userSeeder(10);
        volunteerSeeder.volunteerSeeder(10);
        emergencySeeder.emergencySeeder(10);
        taskSeeder.taskSeeder(10);



    };
}
