package com.budgetwise.api.dto;

import java.util.UUID;

public record UserDto(UUID id, String email, String role) { }
