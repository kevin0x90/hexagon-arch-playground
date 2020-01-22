package github.infrastructure.spring.configuration;

import github.domain.port.GitHubClient;
import github.application.usecase.repository.ListRepositoriesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public ListRepositoriesUseCase listRepositoriesUseCase(final GitHubClient gitHubClient) {
        return new ListRepositoriesUseCase(gitHubClient);
    }
}
