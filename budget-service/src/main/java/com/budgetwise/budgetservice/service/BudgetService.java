package com.budgetwise.budgetservice.service;

import com.budgetwise.budgetservice.domain.Budget;
import com.budgetwise.budgetservice.domain.Transaction;
import com.budgetwise.budgetservice.dto.BudgetRequest;
import com.budgetwise.budgetservice.dto.TransactionRequest;
import com.budgetwise.budgetservice.repository.BudgetRepository;
import com.budgetwise.budgetservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.budgetwise.api.dto.UserDto;
import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgets;
    private final TransactionRepository txns;
    private final WebClient userClient;

    public BudgetService(
            BudgetRepository budgets,
            TransactionRepository txns,
            WebClient userServiceClient
    ) {
        this.budgets = budgets;
        this.txns = txns;
        this.userClient = userServiceClient;
    }

    private Mono<UserDto> fetchUser(String token) {
        return userClient.get()
                .uri("/users/me")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Budget createBudget(String token, BudgetRequest req) {
        String bearer = "Bearer " + token;
        // validate token by calling User-Service
        UserDto user = fetchUser(bearer).block();
        String userId = user.id().toString();
        Budget b = new Budget();
        b.setUserId(userId);
        b.setName(req.name());
        b.setLimit(req.limit());
        return budgets.save(b);
    }

    public List<Budget> listBudgets(String token) {
        String userId = String.valueOf(fetchUser("Bearer " + token).block().id());
        return budgets.findByUserId(userId);
    }

    public Transaction addTransaction(Long budgetId, TransactionRequest req) {
        Transaction t = new Transaction();
        t.setBudgetId(budgetId);
        t.setAmount(req.amount());
        return txns.save(t);
    }

    public List<Transaction> listTransactions(Long budgetId) {
        return txns.findByBudgetId(budgetId);
    }
}

