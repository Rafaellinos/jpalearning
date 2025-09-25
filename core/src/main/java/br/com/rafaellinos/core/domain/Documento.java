package br.com.rafaellinos.core.domain;

import lombok.Getter;

public class Documento {

    private final String cpfCnpj;

    @Getter
    private final TipoPessoa tipoPessoa;

    private Documento(String cpfCnpj, TipoPessoa tipoPessoa) {
        this.cpfCnpj = cpfCnpj;
        this.tipoPessoa = tipoPessoa;
    }

    public static Documento fromString(final String documentoString) {
        if (documentoString.length() == 11) {
            return new Documento(documentoString, TipoPessoa.CPF);
        }
        if (documentoString.length() == 14) {
            return new Documento(documentoString, TipoPessoa.CNPJ);
        }
        throw new RuntimeException("Tipo documento invalido");
    }

    @Override
    public String toString() {
        return this.cpfCnpj;
    }
}
