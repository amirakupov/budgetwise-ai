package com.budgetwise.dto;

import java.util.UUID;

public record UserDto(UUID id, String email, String role) { }
