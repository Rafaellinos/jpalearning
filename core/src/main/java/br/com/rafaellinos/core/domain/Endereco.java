package br.com.rafaellinos.core.domain;

import lombok.Data;

@Data
public class Endereco {

    private String logradouro;
    private String logradouroNumero;
    private String cidade;
    private String uf;
    private Cep cep;
    private Boolean principal;

    public Endereco(String logradouro, String logradouroNumero, String cidade, String uf, Cep cep, Boolean principal) {
        this.logradouro = logradouro;
        this.logradouroNumero = logradouroNumero;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.principal = principal;
    }
}
