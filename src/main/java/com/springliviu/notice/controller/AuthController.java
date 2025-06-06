package com.springliviu.notice.controller;

import com.springliviu.notice.dto.AuthResponse;
import com.springliviu.notice.dto.LoginRequest;
import com.springliviu.notice.dto.RegisterRequest;
import com.springliviu.notice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Exposes methods as REST API endpoints
@RequestMapping("/api/auth") // Base path for authentication-related routes
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        // Handles user registration and returns JWT token
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        // Handles user login and returns JWT token
        return ResponseEntity.ok(authService.login(request));
    }
}
