package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.specification.PessoaSpecification;

import java.util.*;

public interface PessoaUseCase {

    Pessoa save(Pessoa pessoa);

    PageableDomain<Pessoa> getPessoa(PessoaSpecification spec);

    Pessoa update(UUID id, Pessoa domain);

}
