package com.example.practice15.controller;

import com.example.practice15.model.Manufacture;
import com.example.practice15.repository.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufactures")
public class ManufactureController {
    @Autowired
    private ManufactureRepository manufactureRepository;

    @GetMapping
    public List<Manufacture> getAllManufactures() {
        return manufactureRepository.findAll();
    }

    @PostMapping
    public Manufacture createManufacture(@RequestBody Manufacture manufacture) {
        return manufactureRepository.save(manufacture);
    }

    @DeleteMapping("/{id}")
    public void deleteManufacture(@PathVariable Long id) {
        manufactureRepository.deleteById(id);
    }
}
