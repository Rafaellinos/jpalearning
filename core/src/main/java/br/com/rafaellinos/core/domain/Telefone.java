package br.com.rafaellinos.core.domain;

import lombok.Data;

@Data
public class Telefone {

    private String ddd;
    private String telefone;
    private Boolean principal;

    public Telefone(String ddd, String telefone, Boolean principal) {
        this.ddd = ddd;
        this.telefone = telefone;
        this.principal = principal;
    }
}
