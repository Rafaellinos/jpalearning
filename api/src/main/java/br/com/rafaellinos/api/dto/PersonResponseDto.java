package br.com.rafaellinos.api.dto;

import java.util.UUID;

public record PersonResponseDto(
        UUID id,
        String name,
        Integer age
) {
}
