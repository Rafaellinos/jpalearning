package br.com.rafaellinos.api.dto;

public record EnderecoDto(
        String logradouro,
        String cidade,
        String uf,
        String cep,
        String logradouroNumero,
        Boolean principal
) {
}
