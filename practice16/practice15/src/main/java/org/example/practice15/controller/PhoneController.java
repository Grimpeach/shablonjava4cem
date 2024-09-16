package org.example.practice15.controller;

import org.example.practice15.model.Phone;
import org.example.practice15.repository.PhoneRepository;
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
    private PhoneRepository phoneRepository;

    // Получение всех телефонов
    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    // Получение телефона по ID
    @GetMapping("/{id}")
    public ResponseEntity<Phone> getPhoneById(@PathVariable Long id) {
        Optional<Phone> phone = phoneRepository.findById(id);
        if (phone.isPresent()) {
            return ResponseEntity.ok(phone.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Создание нового телефона
    @PostMapping
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone) {
        if (phone.getName() == null || phone.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);  // Проверка на пустое имя
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneRepository.save(phone));
    }

    // Удаление телефона по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
