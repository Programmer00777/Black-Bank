package com.blackbank.app.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {
    @GetMapping("/")
    public String index() {
        return "You have logged in!";
    }
}
