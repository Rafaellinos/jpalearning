package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.*;
import br.com.rafaellinos.core.repository.*;
import br.com.rafaellinos.core.specification.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

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

    @Override
    public Pessoa update(UUID id, Pessoa pessoa) {

        PessoaSpecification spec = PessoaSpecification.builder()
                .withId(id)
                .withPageNumber(0)
                .withPageSize(1)
                .build();

        PageableDomain<Pessoa> resultado = pessoaRepository.get(spec);

        if (resultado.getContent().isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        Pessoa pessoaExistente = resultado.getContent().get(0);

        // PUT = substituição total
        pessoaExistente.setNome(pessoa.getNome());
        pessoaExistente.setSobrenome(pessoa.getSobrenome());
        pessoaExistente.setDocumento(pessoa.getDocumento());
        pessoaExistente.setIdade(pessoa.getIdade());
        pessoaExistente.setEmails(pessoa.getEmails());
        pessoaExistente.setEnderecos(pessoa.getEnderecos());
        pessoaExistente.setTelefones(pessoa.getTelefones());

        return pessoaRepository.save(pessoaExistente);
    }
}