package br.com.rafaellinos.repository;

import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.PersonQualification;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.repository.PessoaRepository;
import br.com.rafaellinos.core.specification.PessoaSpecification;
import br.com.rafaellinos.repository.entity.PessoaEntity;
import br.com.rafaellinos.repository.entity.PessoaQualificationEntity;
import br.com.rafaellinos.repository.jparepository.PessoaJpaRepository;
import br.com.rafaellinos.repository.mapper.PessoaEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaJpaRepository pessoaJpaRepository;
    private final PessoaEntityMapper mapper;

    @Override
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        PessoaEntity pessoaEntity = mapper.toEntity(pessoa);
        PessoaQualificationEntity pessoaQualificationEntity = new PessoaQualificationEntity();
        pessoaQualificationEntity.setId((long) PersonQualification.UNKWON.getCodigo());
        pessoaEntity.setQualification(pessoaQualificationEntity);
        return mapper.toDomain(pessoaJpaRepository.save(pessoaEntity));
    }

    @Override
    public PageableDomain<Pessoa> get(PessoaSpecification spec) {
        Specification<PessoaEntity> jpaSpec = ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), spec.getId()));
        Pageable pageable = PageRequest.of(spec.getPageNumber(), spec.getPageSize());
        Page<PessoaEntity> pessoaEntities = pessoaJpaRepository
                .findAll(jpaSpec, pageable);
        PageableDomain<Pessoa> pessoaPageableDomain = new PageableDomain<>();
        pessoaPageableDomain.setContent(pessoaEntities.stream().map(mapper::toDomain).toList());
        pessoaPageableDomain.setTotalPages(pessoaEntities.getTotalPages());
        pessoaPageableDomain.setTotalElements(pessoaEntities.getNumberOfElements());
        return pessoaPageableDomain;
    }
}
