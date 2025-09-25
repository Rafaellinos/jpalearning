package br.com.rafaellinos.core.domain;

public class Cep {

    private final String cepString;

    public Cep(String cep) {
        this.cepString = cep;
    }

    public static Cep fromString(final String cep) {
        return new Cep(cep);
    }

    @Override
    public String toString() {
        return this.cepString;
    }
}
