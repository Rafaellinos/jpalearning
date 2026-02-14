package br.com.rafaellinos.api.dto.request;

import java.util.List;
import java.util.UUID;

public record PersonQueryParam(
        UUID personId,
        String nome,
        String sobrenome,
        String documento,
        List<Expand> expands
) {

    // canonical constructor
    public PersonQueryParam {
        expands = expands == null ? List.of() : expands;
    }
}
