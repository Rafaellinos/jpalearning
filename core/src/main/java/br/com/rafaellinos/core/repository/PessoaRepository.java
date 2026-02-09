package br.com.rafaellinos.core.repository;

import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.specification.PessoaSpecification;


import java.util.*;

public interface PessoaRepository {

    Pessoa save(Pessoa pessoa);

    PageableDomain<Pessoa> get(PessoaSpecification spec);

    Optional<Pessoa> findById(UUID id);
}
