package org.example.practice15.controller;


import org.example.practice15.model.Phone;
import org.example.practice15.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Phone> createPhone(@RequestBody Phone phone) {
        if (phone.getName() == null || phone.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(phoneRepository.save(phone));
    }



    @DeleteMapping("/{id}")
    public void deletePhone(@PathVariable Long id) {
        phoneRepository.deleteById(id);
    }
}
