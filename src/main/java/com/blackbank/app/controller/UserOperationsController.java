package com.blackbank.app.controller;

import com.blackbank.app.models.User;
import com.blackbank.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that manages mappings related to performing operations with {@link User} entities.
 *
 * @author sergeychernikov
 * @version 1.0
 */

@Controller
@RequestMapping("/operations")
public class UserOperationsController {

    private final UserRepository userRepository;

    @Autowired
    public UserOperationsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getSearchUserPage(Model model) {

        model.addAttribute("user", new User());

        return "operations/searchUserPage";
    }

    @PostMapping
    public String processSearchUserPage(@RequestParam String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            System.out.println("User with such email doesn't exist");
            return "redirect:/operations";
        }

        return "redirect:/profile/user/" + user.getId();
    }

}
