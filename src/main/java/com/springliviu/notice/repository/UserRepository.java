package com.springliviu.notice.repository;

import com.springliviu.notice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // For login
    boolean existsByEmail(String email); // For registration check
}
