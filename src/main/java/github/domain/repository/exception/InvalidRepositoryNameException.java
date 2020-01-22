package github.domain.repository.exception;

public class InvalidRepositoryNameException extends RuntimeException {

    public InvalidRepositoryNameException(final String message) {
        super(message);
    }
}
