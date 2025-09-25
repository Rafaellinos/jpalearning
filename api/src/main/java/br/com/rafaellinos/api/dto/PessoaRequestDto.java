package br.com.rafaellinos.api.dto;

import java.util.List;

public record PessoaRequestDto(
        String nome,
        Integer idade,
        List<EmailDto> emails,
        String documento,
        List<TelefoneDto> telefones,
        List<EnderecoDto> enderecos
) {
}
