package br.com.rafaellinos.core.specification;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder(setterPrefix = "with")
@Getter
public class PessoaSpecification {

    private UUID id;
    private Integer pageSize;
    private Integer pageNumber;
    private boolean searchEmail;
    private boolean searchTelefone;
    private boolean searchEndereco;
}
