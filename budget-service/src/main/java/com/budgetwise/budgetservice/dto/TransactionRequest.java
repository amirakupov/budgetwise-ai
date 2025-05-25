package com.budgetwise.budgetservice.dto;


import java.math.BigDecimal;

public record TransactionRequest(
        BigDecimal amount
) {}

