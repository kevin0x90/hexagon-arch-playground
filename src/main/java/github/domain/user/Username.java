package github.domain.user;

import github.domain.user.exception.InvalidUsernameException;

import java.util.Objects;

public class Username {

    private final String username;

    private Username(final String username) {
        if (username == null) {
            throw new InvalidUsernameException("username must not be null");
        }

        this.username = username;
    }

    public static Username of(final String username) {
        return new Username(username);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Username username1 = (Username) o;
        return username.equals(username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Username{username='" + username + "'}";
    }

    public String toDto() {
        return username;
    }
}
