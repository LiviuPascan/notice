package com.springliviu.notice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Marks this filter as a Spring-managed bean
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // Reads the Authorization header

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // If no token — pass request along the chain
            return;
        }

        String token = authHeader.substring(7); // Extracts token from header (removes "Bearer ")

        String userEmail = jwtUtil.extractEmail(token); // Extracts email from token

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // If token is valid and user not yet authenticated

            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail); // Loads user from DB

            if (jwtUtil.isTokenValid(token)) {
                // If token is valid — create auth object
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Attach request context

                SecurityContextHolder.getContext().setAuthentication(authToken); // Set user as authenticated
            }
        }

        filterChain.doFilter(request, response); // Continue filter chain
    }
}
