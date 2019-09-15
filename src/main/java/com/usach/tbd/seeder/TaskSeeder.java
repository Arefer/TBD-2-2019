package com.usach.tbd.seeder;

import com.github.javafaker.Faker;
import com.usach.tbd.model.Emergency;
import com.usach.tbd.model.Task;
import com.usach.tbd.model.User;
import com.usach.tbd.repository.EmergencyRepository;
import com.usach.tbd.repository.TaskRepository;
import com.usach.tbd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void taskSeeder(int numberRecords){

        Task task;
        User user;
        Emergency emergency;

        for(int i=0;i < numberRecords;i++){
                task = new Task();

                task.setTitle(faker.job().title());
                task.setStatus(faker.bool().bool());
                task.setPriority(faker.number().randomDigit());
                task.setDescription(faker.lorem().paragraph(2));


                Long qty = userRepository.count();
                user = userRepository.findById((faker.number().numberBetween(1,qty))).get();
                task.setUser(user);

                qty = emergencyRepository.count();
                emergency = emergencyRepository.findById((faker.number().numberBetween(1,qty))).get();
                task.setEmergency(emergency);



                taskRepository.save(task);
        }
    }
}
