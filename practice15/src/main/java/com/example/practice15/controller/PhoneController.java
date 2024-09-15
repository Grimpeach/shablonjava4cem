package com.example.practice15.controller;

import com.example.practice15.model.Phone;
import com.example.practice15.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    @PostMapping
    public Phone createPhone(@RequestBody Phone phone) {
        return phoneRepository.save(phone);
    }

    @DeleteMapping("/{id}")
    public void deletePhone(@PathVariable Long id) {
        phoneRepository.deleteById(id);
    }
}
