package com.springliviu.notice.security;

import com.springliviu.notice.model.User;
import com.springliviu.notice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Marks this as a Spring-managed service bean
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Loads user from database by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Returns Spring Security-compatible UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail()) // Uses email as username
                .password(user.getPassword()) // Sets hashed password
                .authorities("USER") // Simple default role
                .build();
    }
}
