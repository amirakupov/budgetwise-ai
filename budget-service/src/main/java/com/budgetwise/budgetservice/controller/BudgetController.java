package com.budgetwise.budgetservice.controller;

import com.budgetwise.budgetservice.domain.Budget;
import com.budgetwise.budgetservice.domain.Transaction;
import com.budgetwise.budgetservice.dto.BudgetRequest;
import com.budgetwise.budgetservice.dto.TransactionRequest;
import com.budgetwise.budgetservice.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService service;

    public BudgetController(BudgetService service) { this.service = service; }

    @PostMapping
    public Budget create(
            @RequestHeader("Authorization") String auth,
            @RequestBody BudgetRequest req
    ) {
        return service.createBudget(auth.substring(7), req);
    }

    @GetMapping
    public List<Budget> list(@RequestHeader("Authorization") String auth) {
        return service.listBudgets(auth.substring(7));
    }

    @PostMapping("/{id}/transactions")
    public Transaction txn(
            @PathVariable Long id,
            @RequestBody TransactionRequest req
    ) {
        return service.addTransaction(id, req);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> txns(@PathVariable Long id) {
        return service.listTransactions(id);
    }
}
