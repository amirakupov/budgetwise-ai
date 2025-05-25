package com.budgetwise.budgetservice.repository;

import com.budgetwise.budgetservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByBudgetId(Long budgetId);
}
