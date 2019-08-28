package com.usach.tbd.controller;

import com.usach.tbd.model.Characteristic;
import com.usach.tbd.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Characteristics")
public class CharacteristicController {
    private CharacteristicRepository characteristicRepository;

    @Autowired
    public CharacteristicController(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Characteristic> listCharacteristics() {
        return (List<Characteristic>) this.characteristicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Characteristic createCharacteristic(@RequestBody Characteristic characteristic) {
        return this.characteristicRepository.save(characteristic);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Characteristic getCharacteristic(@PathVariable Long id) {
        return this.characteristicRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Characteristic updateCharacteristic(@PathVariable String id, @RequestBody Characteristic characteristic) {
        if (!characteristic.getId().equals(id)){
            return null;
        }
        return this.characteristicRepository.save(characteristic);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Characteristic deleteCharacteristic(@PathVariable Long id) {
        Optional<Characteristic> characteristic = this.characteristicRepository.findById(id);
        if (characteristic.isPresent()) {
            this.characteristicRepository.delete(characteristic.get());
        }
        return null;
    }
}