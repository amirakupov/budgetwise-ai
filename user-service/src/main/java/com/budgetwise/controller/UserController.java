package com.budgetwise.controller;

import com.budgetwise.dto.UserDto;
import com.budgetwise.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService users;

    public UserController(UserService users) { this.users = users; }

    @GetMapping("/me")
    public UserDto me(Authentication auth) {
        return users.getCurrentUser(auth);
    }
}
