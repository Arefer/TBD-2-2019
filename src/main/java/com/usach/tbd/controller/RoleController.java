package com.usach.tbd.controller;


import com.usach.tbd.model.Role;
import com.usach.tbd.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/roles")
public class RoleController {
    private RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Role> listRoles() {
        return (List<Role>) this.roleRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Role createRole(@RequestBody Role role) {
        return this.roleRepository.save(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role getRole(@PathVariable Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Role updateRole(@PathVariable String id, @RequestBody Role role) {
        if (!role.getId().equals(id)) {
            return null;
        }
        return this.roleRepository.save(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Role deleteRole(@PathVariable Long id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (role.isPresent()) {
            this.roleRepository.delete(role.get());
        }
        return null;
    }
}