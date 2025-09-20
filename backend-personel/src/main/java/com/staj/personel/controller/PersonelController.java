package com.staj.personel.controller;

import org.springframework.web.bind.annotation.*;
import com.staj.personel.model.Personel;
import com.staj.personel.service.PersonelService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/personel")
public class PersonelController {

    private final PersonelService personelService;

    public PersonelController(PersonelService personelService) {
        this.personelService = personelService;
    }

    @PostMapping
    public Personel addPersonel(@RequestBody Personel personel) {
        return personelService.createPersonel(personel);
    }

    @GetMapping
    public List<Personel> getAllPersonel() {
        return personelService.getAllPersonel();
    }

    @GetMapping("/{id}")
    public Personel getPersonelById(@PathVariable Long id) {
        return personelService.getPersonelById(id)
                .orElseThrow(() -> new RuntimeException("Personel not found with id " + id));
    }

    @PutMapping("/{id}")
    public Personel updatePersonel(@PathVariable Long id, @RequestBody Personel personelDetails) {
        return personelService.updatePersonel(id, personelDetails);
    }

    @DeleteMapping("/{id}")
    public void deletePersonel(@PathVariable Long id) {
        personelService.deletePersonel(id);
    }
}
