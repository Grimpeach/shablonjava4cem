package org.example.practice15.controller;

import org.example.practice15.model.Manufacture;
import org.example.practice15.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufactures")
public class ManufactureController {

    @Autowired
    private ManufactureService manufactureService;

    // Получение всех производителей
    @GetMapping
    public ResponseEntity<List<Manufacture>> getAllManufactures(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address) {
        List<Manufacture> manufactures = manufactureService.findManufactures(name, address);
        return ResponseEntity.ok(manufactures);
    }

    // Получение производителя по ID
    @GetMapping("/{id}")
    public ResponseEntity<Manufacture> getManufactureById(@PathVariable Long id) {
        return manufactureService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Создание нового производителя
    @PostMapping
    public ResponseEntity<Manufacture> createManufacture(@RequestBody Manufacture manufacture) {
        if (manufacture.getName() == null || manufacture.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(manufactureService.save(manufacture));
    }

    // Удаление производителя по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManufacture(@PathVariable Long id) {
        if (!manufactureService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        manufactureService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
