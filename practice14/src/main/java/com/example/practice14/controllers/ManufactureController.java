package com.example.practice14.controllers;

import com.example.practice14.model.Manufacture;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manufactures")
public class ManufactureController {

    private List<Manufacture> manufactures = new ArrayList<>();

    @GetMapping
    public List<Manufacture> getManufactures() {
        return manufactures;
    }

    @PostMapping
    public void addManufacture(@RequestBody Manufacture manufacture) {
        manufactures.add(manufacture);
    }

    @DeleteMapping("/{name}")
    public void deleteManufacture(@PathVariable String name) {
        manufactures.removeIf(manufacture -> manufacture.getName().equals(name));
    }
}
