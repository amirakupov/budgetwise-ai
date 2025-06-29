package com.budgetwise.userservice.service;


import com.budgetwise.api.dto.UserDto;
import com.budgetwise.userservice.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserDto getCurrentUser(Authentication auth) {
        String email = auth.getName();
        var u = repo.findByEmail(email).orElseThrow();
        return new UserDto(u.getId(), u.getEmail(), u.getRole());
    }
}
