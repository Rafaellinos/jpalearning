package br.com.rafaellinos.api.dto;

import java.util.List;

public record PageableDto<T>(
        List<T> content,
        int totalPages,
        int totalElements
) {
}
