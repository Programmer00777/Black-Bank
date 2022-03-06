package com.blackbank.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Main controller for managing banking operations between users' accounts.
 *
 * @author sergeychernikov
 * @version 1.0
 */

@Controller
@RequestMapping("/profile")
public class MainController {

    @GetMapping("/")
    public String index(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "mainPage";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/owner")
    public String getOwnerPage() {
        return "ownerPage";
    }


}
