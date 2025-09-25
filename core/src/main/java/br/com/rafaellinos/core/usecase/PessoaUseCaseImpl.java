package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.repository.PessoaRepository;
import br.com.rafaellinos.core.specification.PessoaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaUseCaseImpl implements PessoaUseCase {

    private final PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public PageableDomain<Pessoa> getPessoa(PessoaSpecification spec) {
        return pessoaRepository.get(spec);
    }
}
