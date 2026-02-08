package br.com.rafaellinos.core.domain;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Pessoa {

    private UUID pessoaId;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private List<Email> emails;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    private Documento documento;

}
