package github.infrastructure.spring.web.controller;

import github.application.usecase.repository.ListRepositoriesUseCase;
import github.domain.output.repository.RepositoryNameDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RestController
public class RepositoryListController {

    private final ListRepositoriesUseCase listRepositoriesUseCase;

    public RepositoryListController(final ListRepositoriesUseCase listRepositoriesUseCase) {
        this.listRepositoriesUseCase = Objects.requireNonNull(listRepositoriesUseCase, "listRepositoriesUseCase must not be null");
    }

    @GetMapping("/api/repository/list/{username}")
    public CompletableFuture<Set<RepositoryNameDto>> listForUsername(@PathVariable final String username) {
        return listRepositoriesUseCase.listAllFor(username);
    }
}
