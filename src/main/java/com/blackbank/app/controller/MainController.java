package com.blackbank.app.controller;

import com.blackbank.app.models.Role;
import com.blackbank.app.models.Status;
import com.blackbank.app.models.User;
import com.blackbank.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Main controller for managing banking operations between users' accounts.
 *
 * @author sergeychernikov
 * @version 2.0
 */

@Controller
@RequestMapping("/profile")
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

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(principalName).orElse(null);

        model.addAttribute("username", user.getFullName());

        return "adminPage";
    }

    @PostMapping("/admin")
    public String processAdminPage(@RequestParam("userEmail") String userEmail, @RequestParam("operation") String operation) {
        User user = userRepository.findByEmail(userEmail).orElse(null);

        if (user == null) {
            System.out.println("Such user doesn't exist");
            return "redirect:/profile/admin";
        }

        if (userEmail.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            System.out.println("You cannot perform any operation on yourself (block or change a role)");
            return "redirect:/profile/admin";
        }

        if (!user.getRole().name().equals("USER")) {
            System.out.println("You cannot perform any operation on this user");
            return "redirect:/profile/admin";
        }

        switch (operation) {
            case("block"):
                user.setStatus(Status.BANNED);
                userRepository.save(user);
                return "redirect:/profile/admin";

            case("change_role"):
                user.setRole(Role.ADMIN);
                userRepository.save(user);
                return "redirect:/profile/admin";
        }

        return "redirect:/profile/admin";

    }

    @GetMapping("/owner")
    public String getOwnerPage(Model model) {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(principalName).orElse(null);

        model.addAttribute("username", user.getFullName());

        return "ownerPage";
    }

    @PostMapping("/owner")
    public String processOwnerPage(@RequestParam("userEmail") String userEmail, @RequestParam("operation") String operation) {

        User user = userRepository.findByEmail(userEmail).orElse(null);

        if (user == null) {
            System.out.println("Such user doesn't exist");
            return "redirect:/profile/owner";
        }

        if (userEmail.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            System.out.println("You cannot perform any operation on yourself");
            return "redirect:/profile/owner";
        }

        switch (operation) {
            case ("block"):
                if (user.getStatus().name().equals("BANNED")) {
                    System.out.println("User is already blocked");
                } else {
                    user.setStatus(Status.BANNED);
                    userRepository.save(user);
                }
                break;

            case ("unblock"):
                if (user.getStatus().name().equals("ACTIVE")) {
                    System.out.println("User is already active");
                } else {
                    user.setStatus(Status.ACTIVE);
                    userRepository.save(user);
                }
                break;

            case ("set_admin_role"):
                if (user.getRole().name().equals("ADMIN")) {
                    System.out.println("User already has an ADMIN role");
                } else {
                    user.setRole(Role.ADMIN);
                    userRepository.save(user);
                }
                break;

            case ("set_user_role"):
                if (user.getRole().name().equals("USER")) {
                    System.out.println("User already has a USER role");
                } else {
                    user.setRole(Role.USER);
                    userRepository.save(user);
                }
                break;

            case ("delete"):
                userRepository.delete(user);
                break;
        }

        return "redirect:/profile/owner";
    }

    @GetMapping("/user/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model) {

        User targetUser = userRepository.findById(id).orElse(null);

        if (targetUser == null) {
            return "redirect:/profile/";
        }

        model.addAttribute("username", targetUser.getFullName());
        model.addAttribute("user", targetUser);
        model.addAttribute("userRole", targetUser.getRole().name());
        model.addAttribute("userStatus", targetUser.getStatus().name());

        return "userPage";
    }
}
