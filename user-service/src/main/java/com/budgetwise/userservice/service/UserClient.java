package com.budgetwise.userservice.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.budgetwise.api.dto.UserDto;

@Component
public class UserClient {
    private final WebClient wc;
    public UserClient(WebClient.Builder b) {
        // user-service:80 inside the cluster
        this.wc = b.baseUrl("http://user-service").build();
    }
    public Mono<UserDto> getUser(UUID id) {
        return wc.get()
                .uri("/api/users/{id}", id)
                .retrieve()
                .bodyToMono(UserDto.class);
    }
}

