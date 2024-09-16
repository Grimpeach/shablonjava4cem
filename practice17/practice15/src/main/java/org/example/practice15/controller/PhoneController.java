package org.example.practice15.controller;

import org.example.practice15.model.Phone;
import org.example.practice15.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    // Получение всех телефонов
    @GetMapping
    public ResponseEntity<List<Phone>> getAllPhones(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer creationYear,
            @RequestParam(required = false) Long manufactureId) {
        List<Phone> phones = phoneService.findPhones(name, creationYear, manufactureId);
        return ResponseEntity.ok(phones);
    }

    // Получение телефона по ID
    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Long id) {
        Optional<Phone> phone = phoneService.findById(id);
        return phone.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Создание нового телефона
    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone) {
        if (phone.getName() == null || phone.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);  // Проверка на пустое имя
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.save(phone));
    }

    // Удаление телефона по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        if (phoneService.existsById(id)) {
            phoneService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
