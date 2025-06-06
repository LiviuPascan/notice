package com.springliviu.notice.service;

import com.springliviu.notice.dto.AuthResponse;
import com.springliviu.notice.dto.LoginRequest;
import com.springliviu.notice.dto.RegisterRequest;
import com.springliviu.notice.model.User;
import com.springliviu.notice.repository.UserRepository;
import com.springliviu.notice.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Marks this class as a Spring service component
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use"); // Prevent duplicate registration
        }

        User user = User.builder()
                .email(request.getEmail()) // Sets email from request
                .password(passwordEncoder.encode(request.getPassword())) // Encodes password
                .build();

        userRepository.save(user); // Saves new user
        String token = jwtUtil.generateToken(user.getEmail()); // Generates token

        return new AuthResponse(token); // Returns JWT response
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password")); // Checks user existence

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password"); // Validates password
        }

        String token = jwtUtil.generateToken(user.getEmail()); // Generates token
        return new AuthResponse(token); // Returns JWT response
    }
}
