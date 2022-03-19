package com.blackbank.app.controller;

import com.blackbank.app.service.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/email")
public class EmailController {

    private final EmailServiceImplementation emailServiceImplementation;

    @Autowired
    public EmailController(EmailServiceImplementation emailServiceImplementation) {
        this.emailServiceImplementation = emailServiceImplementation;
    }

    @GetMapping("/")
    public String getMainEmailPage(Model model) {
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        return "email/sendEmailPage";
    }

    @PostMapping("/")
    public String processEmailPage(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("text") String text) throws MessagingException {
        emailServiceImplementation.send(to, subject, text);

        return "redirect:/email/";
    }
}
