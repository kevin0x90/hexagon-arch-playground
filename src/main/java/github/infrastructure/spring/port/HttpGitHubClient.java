package github.infrastructure.spring.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.domain.port.GitHubClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class HttpGitHubClient implements GitHubClient {

    private static final String LIST_REPOS_URI = "https://api.github.com/users/%s/repos?type=all&per_page=100";
    private final ObjectMapper objectMapper;

    public HttpGitHubClient(final ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public CompletableFuture<Set<String>> listRepositoryNames(final String username) {

        final var httpClient = HttpClient.newBuilder()
                .build();

        final var request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/json")
                .uri(URI.create(String.format(LIST_REPOS_URI, username)))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(this::deserialize);
    }

    private Set<String> deserialize(final HttpResponse<String> response) {

        try {
            final var repositoriesJsonArray = objectMapper.readTree(response.body());

            return StreamSupport.stream(repositoriesJsonArray.spliterator(), false)
                    .map(node -> node.get("name").asText())
                    .collect(Collectors.toUnmodifiableSet());
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
