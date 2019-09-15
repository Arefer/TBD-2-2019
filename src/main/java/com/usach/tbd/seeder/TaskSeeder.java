package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.usach.tbd.model.Emergency;
import com.usach.tbd.model.Task;
import com.usach.tbd.model.User;
import com.usach.tbd.repository.EmergencyRepository;
import com.usach.tbd.repository.TaskRepository;
import com.usach.tbd.repository.UserRepository;
import com.usach.tbd.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Locale;

@Component
public class TaskSeeder {

    private Faker faker = new Faker(new Locale("es"));
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private VolunteerRepository volunteerRepository;

    public void taskSeeder(int numberRecords){

        Task task;
        User user;
        Emergency emergency;


        Long userQty = userRepository.count();
        Long emergencyQty = emergencyRepository.count();
        Long volunteerQty = volunteerRepository.count();

        for(int i=0;i < numberRecords;i++){
                task = new Task();

                task.setTitle(faker.job().title());
                task.setStatus(faker.bool().bool());
                task.setPriority(faker.number().randomDigit());
                task.setDescription(faker.lorem().paragraph(2));


                user = userRepository.findById((faker.number().numberBetween(1,userQty))).get();
                task.setUser(user);

                emergency = emergencyRepository.findById((faker.number().numberBetween(1,emergencyQty))).get();
                task.setEmergency(emergency);

                //Many to many
                task.setVolunteers(new HashSet<>());

                for(int n=0;n<1;n++){
                    task.getVolunteers().add(volunteerRepository.findById(faker.number().numberBetween(1,volunteerQty)).get());
                }

                taskRepository.save(task);
        }
    }
}
