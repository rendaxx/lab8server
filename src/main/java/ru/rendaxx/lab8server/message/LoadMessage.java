package ru.rendaxx.lab8server.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.rendaxx.lab8server.dto.OrganizationDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
public class LoadMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 124245345834L;
    private Collection<OrganizationDto> payload;
}
