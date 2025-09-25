package br.com.rafaellinos.api.dto;

import java.util.List;
import java.util.UUID;

public record PessoaResponseDto(
        UUID id,
        String name,
        Integer age,
        List<EmailDto> emails,
        List<TelefoneDto> telefones,
        List<EnderecoDto> enderecos
) {
}
