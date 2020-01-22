package github.domain.port;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface GitHubClient {

    CompletableFuture<Set<String>> listRepositoryNames(final String username);
}
