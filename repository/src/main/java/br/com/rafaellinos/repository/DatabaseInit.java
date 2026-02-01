package br.com.rafaellinos.repository;

import br.com.rafaellinos.core.domain.PersonQualification;
import br.com.rafaellinos.repository.entity.PessoaQualificationEntity;
import br.com.rafaellinos.repository.jparepository.PessoaQualificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("init")
public class DatabaseInit implements CommandLineRunner {

    private final PessoaQualificationJpaRepository pessoaQualificationJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        List<PessoaQualificationEntity> dados = pessoaQualificationJpaRepository.findAll();
        for (PersonQualification personQualification : PersonQualification.values()) {
            if (dados.stream()
                    .anyMatch(a -> a.getId().equals((long) personQualification.getCodigo()))) {
                continue;
            }
            PessoaQualificationEntity pessoaQualificationEntity = new PessoaQualificationEntity();
            pessoaQualificationEntity.setId((long) PersonQualification.UNKWON.getCodigo());
            pessoaQualificationEntity.setName(PersonQualification.UNKWON.name());
            pessoaQualificationJpaRepository.save(pessoaQualificationEntity);
        }

    }
}
