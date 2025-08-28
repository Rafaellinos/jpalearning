package br.com.rafaellinos.repository.jparepository;

import br.com.rafaellinos.repository.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, UUID> {
}
