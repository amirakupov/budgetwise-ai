package com.budgetwise.userservice.service;

import com.budgetwise.userservice.domain.User;
import com.budgetwise.userservice.dto.LoginRequest;
import com.budgetwise.userservice.dto.RegisterRequest;
import com.budgetwise.userservice.repository.UserRepository;
import com.budgetwise.userservice.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        String hash = passwordEncoder.encode(req.password());
        User u = new User();
        u.setEmail(req.email());
        u.setPasswordHash(hash);
        repo.save(u);
        return jwtUtil.generateToken(u.getEmail());
    }

    public String login(LoginRequest req) {
        User u = repo.findByEmail(req.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        // use matches() instead of verifyer
        if (!passwordEncoder.matches(req.password(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return jwtUtil.generateToken(u.getEmail());
    }
}

