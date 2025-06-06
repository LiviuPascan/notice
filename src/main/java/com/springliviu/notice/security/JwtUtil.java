package com.springliviu.notice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Makes this class a Spring-managed bean
public class JwtUtil {

    private Key key; // Secret key used to sign JWT tokens

    private final long expiration = 1000 * 60 * 60 * 24; // Token validity: 24 hours

    @PostConstruct
    public void init() {
        // Generate HMAC SHA-256 key on app startup (in-memory)
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String email) {
        // Builds a new JWT token with subject = user's email
        return Jwts.builder()
                .setSubject(email) // Subject = user email
                .setIssuedAt(new Date()) // Token issue time
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Expiry
                .signWith(key) // Signs the token with secret key
                .compact(); // Builds the token
    }

    public String extractEmail(String token) {
        // Extracts email (subject) from JWT
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token); // Parses and verifies the token
            return true; // Token is valid
        } catch (JwtException e) {
            return false; // Token is invalid or expired
        }
    }

    private Claims getClaims(String token) {
        // Parses JWT and returns its claims
        return Jwts.parserBuilder()
                .setSigningKey(key) // Sets key to verify signature
                .build()
                .parseClaimsJws(token) // Parses the token
                .getBody(); // Returns claims
    }
}
