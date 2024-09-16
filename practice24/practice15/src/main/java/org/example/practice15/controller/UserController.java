package org.example.practice15.controller;

import org.example.practice15.model.User;
import org.example.practice15.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Эндпоинт для регистрации
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Пользователь с таким именем уже существует";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Пользователь успешно зарегистрирован";
    }

    // Эндпоинт для авторизации (если используете REST)
    @PostMapping("/login")
    public String login() {
        return "Авторизация";
    }
}
