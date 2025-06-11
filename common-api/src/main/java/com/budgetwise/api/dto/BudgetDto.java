package com.budgetwise.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record BudgetDto(
        UUID id,
        String name,
        BigDecimal limit,
        UUID userId,
        Instant createdAt
) {}
