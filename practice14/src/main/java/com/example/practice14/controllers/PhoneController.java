package com.example.practice14.controllers;

import com.example.practice14.model.Phone;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private List<Phone> phones = new ArrayList<>();

    @GetMapping
    public List<Phone> getPhones() {
        return phones;
    }

    @PostMapping
    public void addPhone(@RequestBody Phone phone) {
        phones.add(phone);
    }

    @DeleteMapping("/{name}")
    public void deletePhone(@PathVariable String name) {
        phones.removeIf(phone -> phone.getName().equals(name));
    }
}
