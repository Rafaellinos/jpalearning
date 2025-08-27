package br.com.rafaellinos.repository.jparepository;

import br.com.rafaellinos.repository.entity.PersonQualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonQualificationRepository extends JpaRepository<PersonQualificationEntity, Long> {
}
