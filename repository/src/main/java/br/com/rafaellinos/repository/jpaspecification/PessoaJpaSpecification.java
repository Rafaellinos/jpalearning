package br.com.rafaellinos.repository.jpaspecification;

import br.com.rafaellinos.core.specification.PessoaSpecification;
import br.com.rafaellinos.repository.entity.PessoaEntity;
import br.com.rafaellinos.repository.entity.PessoaEntity_;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PessoaJpaSpecification {

    public static Specification<PessoaEntity> from(PessoaSpecification spec) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (spec.getDocumento() != null) {
                predicates.add(criteriaBuilder.equal(root.get(PessoaEntity_.documento), spec.getDocumento()));
            }
            if (spec.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get(PessoaEntity_.id), spec.getId()));
            }
            if (spec.getNome() != null && !spec.getNome().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(PessoaEntity_.name)),
                        "%" + spec.getNome().toLowerCase() + "%"
                ));
            }
            if (spec.getSobrenome() != null && !spec.getSobrenome().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(PessoaEntity_.sobrenome)),
                        "%" + spec.getSobrenome().toLowerCase() + "%"
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
