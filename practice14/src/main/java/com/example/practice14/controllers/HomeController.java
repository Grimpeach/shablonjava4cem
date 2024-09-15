package com.example.practice14.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        return "index";  // Возвращает HTML-файл с названием "index.html"
    }
}
