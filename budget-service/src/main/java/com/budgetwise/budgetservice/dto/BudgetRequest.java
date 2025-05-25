package com.budgetwise.budgetservice.dto;

import java.math.BigDecimal;

public record BudgetRequest(
        String name,
        BigDecimal limit
) {}

