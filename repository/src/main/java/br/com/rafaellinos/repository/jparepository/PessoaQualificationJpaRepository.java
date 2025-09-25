package br.com.rafaellinos.repository.jparepository;

import br.com.rafaellinos.repository.entity.PessoaQualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaQualificationJpaRepository extends JpaRepository<PessoaQualificationEntity, Long> {
}
