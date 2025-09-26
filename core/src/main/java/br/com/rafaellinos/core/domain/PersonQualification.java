package br.com.rafaellinos.core.domain;

import lombok.Getter;

@Getter
public enum PersonQualification {
    UNKWON(1);

    private final int codigo;

    PersonQualification(int codigo) {
        this.codigo = codigo;
    }
}
