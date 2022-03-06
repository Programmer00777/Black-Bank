package com.blackbank.app.controller;

import com.blackbank.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Main controller for managing banking operations between users' accounts.
 *
 * @author sergeychernikov
 * @version 1.0
 */

@Controller
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Principal principal, Model model) {

        model.addAttribute("username", principal.getName());

        return "mainPage";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "userPage";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminPage";
    }


}
