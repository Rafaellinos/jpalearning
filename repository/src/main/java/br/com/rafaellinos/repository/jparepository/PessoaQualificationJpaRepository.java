package br.com.rafaellinos.repository.jparepository;

import br.com.rafaellinos.repository.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PessoaQualificationJpaRepository extends JpaRepository<PessoaQualificationEntity, Long> {

}
