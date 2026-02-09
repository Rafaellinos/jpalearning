package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.*;
import br.com.rafaellinos.core.repository.*;
import br.com.rafaellinos.core.specification.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
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

        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        if (pessoa.getNome() != null) {
            pessoaExistente.setNome(pessoa.getNome());
        }

        if (pessoa.getSobrenome() != null) {
            pessoaExistente.setSobrenome(pessoa.getSobrenome());
        }

        if (pessoa.getDocumento() != null) {
            pessoaExistente.setDocumento(pessoa.getDocumento());
        }

        if (pessoa.getIdade() != null) {
            pessoaExistente.setIdade(pessoa.getIdade());
        }

        if (pessoa.getEmails() != null) {
            pessoaExistente.setEmails(pessoa.getEmails());
        }

        if (pessoa.getEnderecos() != null) {
            pessoaExistente.setEnderecos(pessoa.getEnderecos());
        }

        if (pessoa.getTelefones() != null) {
            pessoaExistente.setTelefones(pessoa.getTelefones());
        }

        return pessoaRepository.save(pessoaExistente);
    }
}