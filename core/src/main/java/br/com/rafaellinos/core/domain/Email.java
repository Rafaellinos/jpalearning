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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }
}
