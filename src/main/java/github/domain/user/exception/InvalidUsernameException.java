package github.domain.user.exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(final String message) {
        super(message);
    }
}
