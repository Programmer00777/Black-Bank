package com.blackbank.app.service;

import com.blackbank.app.models.User;
import com.blackbank.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SuppressWarnings("all")
    @Override
    public synchronized void transferMoneyTo(String email, Long amount){
        User targetUser = userRepository.findByEmail(email).orElse(null);
        Object principalEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByEmail(String.valueOf(principalEmail)).orElse(null);

        if (targetUser == null) {
            System.out.println("Such user doesn't exist");
            return;
        }

        if (currentUser.getBalance() < amount) {
            System.out.println("There are not enough funds on the account");
            return;
        }

        currentUser.setBalance(currentUser.getBalance() - amount);
        targetUser.setBalance(targetUser.getBalance() + amount);

        userRepository.save(currentUser);
        userRepository.save(targetUser);
    }
}
