package br.com.rafaellinos.api.dto;

import java.util.List;

public record PersonRequestDto(
        String name,
        Integer age,
        List<EmailDto> emails,
        List<TelefoneDto> telefones,
        List<EnderecoDto> enderecos
) {
}
