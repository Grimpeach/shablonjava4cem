package org.example.practice15.controller;

import org.example.practice15.model.Manufacture;
import org.example.practice15.repository.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Manufacture> createManufacture(@RequestBody Manufacture manufacture) {
        if (manufacture.getName() == null || manufacture.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(manufactureRepository.save(manufacture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacture(@PathVariable Long id) {
        if (!manufactureRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        manufactureRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
