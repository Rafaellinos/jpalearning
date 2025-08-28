package br.com.rafaellinos.api.dto;

public record TelefoneDto(
        String ddd,
        String telefone,
        Boolean principal
) {
}
