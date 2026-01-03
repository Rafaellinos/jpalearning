package br.com.rafaellinos.api.dto.request;

import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.UUID;

public record PersonQueryParam(
        UUID personId,
        List<Expand> expands
) {

    // canonical constructor
    public PersonQueryParam {
        expands = expands == null ? List.of() : expands;
    }
}
