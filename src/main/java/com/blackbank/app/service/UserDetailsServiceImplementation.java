package com.blackbank.app.service;

import com.blackbank.app.models.User;
import com.blackbank.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    // FIX THIS CLASS. AFTER ITS DECLARATION AND INITIALIZATION SECURITY ISN'T WORKING

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // We distinguish users by their emails
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        // Here our custom user converts to the user with which Spring Security works
        return SecurityUser.convertUser(user);
    }
}
