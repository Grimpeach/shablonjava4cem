package com.example.practice14task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}