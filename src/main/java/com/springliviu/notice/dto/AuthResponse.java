package com.springliviu.notice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // Lombok generates getter methods
@Setter // Lombok generates setter methods
public class AuthResponse {

    private String token; // JWT access token

    public AuthResponse(String token) {
        this.token = token; // Constructor to initialize token
    }
}
