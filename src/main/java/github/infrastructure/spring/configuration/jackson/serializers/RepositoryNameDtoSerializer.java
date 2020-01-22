package github.infrastructure.spring.configuration.jackson.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import github.domain.output.repository.RepositoryNameDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class RepositoryNameDtoSerializer extends StdSerializer<RepositoryNameDto> {
    public RepositoryNameDtoSerializer() {
        this(null);
    }

    public RepositoryNameDtoSerializer(final Class<RepositoryNameDto> t) {
        super(t);
    }

    @Override
    public void serialize(final RepositoryNameDto repositoryNameDto, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(repositoryNameDto.getName());
    }
}
