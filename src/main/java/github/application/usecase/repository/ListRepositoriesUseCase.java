package github.application.usecase.repository;

import github.domain.output.repository.RepositoryNameDto;
import github.domain.port.GitHubClient;
import github.domain.user.Username;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ListRepositoriesUseCase {

    private final GitHubClient gitHubClient;

    public ListRepositoriesUseCase(final GitHubClient gitHubClient) {
        this.gitHubClient = Objects.requireNonNull(gitHubClient, "gitHubClient must not be null");
    }

    public CompletableFuture<Set<RepositoryNameDto>> listAllFor(final String inputUsername) {
        final var username = Username.of(inputUsername);

        return gitHubClient.listRepositoryNames(username.toDto())
                .thenApply(this::toRepositoryNameOutputDto);
    }

    private Set<RepositoryNameDto> toRepositoryNameOutputDto(final Set<String> repositoryNames) {
        return repositoryNames.stream()
                .map(RepositoryNameDto::new)
                .collect(Collectors.toUnmodifiableSet());
    }
}
