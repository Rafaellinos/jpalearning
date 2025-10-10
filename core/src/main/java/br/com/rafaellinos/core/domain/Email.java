package br.com.rafaellinos.core.domain;

import lombok.Data;

@Data
public class Email {

    private String email;
    private Boolean principal;

    public Email(String email, Boolean principal) {
        this.email = email;
        this.principal = principal;
    }
}
