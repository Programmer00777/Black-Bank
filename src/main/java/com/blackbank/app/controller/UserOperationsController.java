package com.blackbank.app.controller;

import com.blackbank.app.models.User;
import com.blackbank.app.repository.UserRepository;
import com.blackbank.app.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserOperationsController(UserRepository userRepository, UserServiceImplementation userServiceImplementation) {
        this.userRepository = userRepository;
        this.userServiceImplementation = userServiceImplementation;
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

    @GetMapping("/transferMoney")
    public String getTransferMoneyPage(Model model) {
        Object principalName = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepository.findByEmail(String.valueOf(principalName)).orElse(null);

        model.addAttribute("userBalance", currentUser.getBalance());

        return "operations/transferMoneyPage";
    }

    @SuppressWarnings(value = "all")
    @PostMapping("/transferMoney")
    public String processTransferMoneyPage(@RequestParam("userEmail") String userEmail, @RequestParam("amount") Long amount) {

        userServiceImplementation.transferMoneyTo(userEmail, amount);

        return "redirect:/operations/transferMoney";
    }
}
