package com.usach.tbd.seeder;

import com.usach.tbd.model.Role;
import com.usach.tbd.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder {

    @Autowired
    private RoleRepository roleRepository;

    public void roleSeeder(){

        Role role = new Role();
        role.setType("Super-admin");
        roleRepository.save(role);

        role = new Role();
        role.setType("Coordinator");
        roleRepository.save(role);

    }
}
