package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.usach.tbd.model.User;
import com.usach.tbd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserSeeder {

    private Faker faker = new Faker(new Locale("es"));

    @Autowired
    private UserRepository userRepository;

    public void userSeeder(int numberRecords){

        User user;
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("es"), new RandomService());

        for(int i=0;i < numberRecords;i++){


            user = new User();

            user.setName(faker.name().firstName());
            user.setEmail(fakeValuesService.bothify("????##@gmail.com"));
            user.setPassword(fakeValuesService.regexify("[a-z1-9]{10}"));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setRut(fakeValuesService.numerify(fakeValuesService.regexify("[1-2]{1}") + "#.###.###-" + fakeValuesService.regexify("[1-9k]{1}")));

            userRepository.save(user);
        }

    }
}
