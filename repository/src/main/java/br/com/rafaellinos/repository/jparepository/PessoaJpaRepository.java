package br.com.rafaellinos.repository.jparepository;

import br.com.rafaellinos.repository.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, UUID>, JpaSpecificationExecutor<PessoaEntity> {
}