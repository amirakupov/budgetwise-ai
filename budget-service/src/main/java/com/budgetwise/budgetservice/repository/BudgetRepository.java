package com.budgetwise.budgetservice.repository;

import com.budgetwise.budgetservice.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
    List<Budget> findByUserId(String userId);
}
