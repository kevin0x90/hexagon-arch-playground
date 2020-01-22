package github.domain.repository;

import github.domain.output.repository.RepositoryNameDto;
import github.domain.repository.exception.InvalidRepositoryNameException;

import java.util.Objects;

public class RepositoryName {

    private final String name;

    private RepositoryName(final String name) {
        if (name == null) {
            throw new InvalidRepositoryNameException("repository name must not be null");
        }

        this.name = name;
    }

    public static RepositoryName of(final String name) {
        return new RepositoryName(name);
    }

    @Override
    public String toString() {
        return "RepositoryName{name='" + name + "}'";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RepositoryName that = (RepositoryName) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public RepositoryNameDto toDto() {
        return new RepositoryNameDto(name);
    }
}
