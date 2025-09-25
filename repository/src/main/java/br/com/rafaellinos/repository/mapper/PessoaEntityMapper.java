package br.com.rafaellinos.repository.mapper;

import br.com.rafaellinos.core.domain.Cep;
import br.com.rafaellinos.core.domain.Documento;
import br.com.rafaellinos.core.domain.Email;
import br.com.rafaellinos.core.domain.Endereco;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.domain.Telefone;
import br.com.rafaellinos.repository.entity.EmailEntity;
import br.com.rafaellinos.repository.entity.EnderecoEntity;
import br.com.rafaellinos.repository.entity.PessoaEntity;
import br.com.rafaellinos.repository.entity.TelefoneEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PessoaEntityMapper {

    default Pessoa toDomain(PessoaEntity entity) {
        if (entity == null) return null;
        Pessoa domain = new Pessoa();
        domain.setPessoaId(entity.getId());
        domain.setNome(entity.getName());
        domain.setIdade(entity.getAge());
        domain.setDocumento(Documento.fromString(entity.getDocumento()));
        domain.setEmails(toDomainEmails(entity.getEmails()));
        domain.setTelefones(toDomainTelefones(entity.getTelefones()));
        domain.setEnderecos(toDomainEnderecos(entity.getEnderecos()));
        return domain;
    }

    default PessoaEntity toEntity(Pessoa domain) {
        if (domain == null) return null;
        PessoaEntity entity = new PessoaEntity();
        entity.setId(domain.getPessoaId());
        entity.setName(domain.getNome());
        entity.setAge(domain.getIdade());
        entity.setDocumento(domain.getDocumento().toString());
//        entity.setQualification(toEntity(domain.getQualification()));
        entity.setEmails(toEntityEmails(domain.getEmails()));
        entity.setTelefones(toEntityTelefones(domain.getTelefones()));
        entity.setEnderecos(toEntityEnderecos(domain.getEnderecos()));
        return entity;
    }

    default Email toDomain(EmailEntity entity) {
        if (entity == null) return null;
        return new Email(
                entity.getEmail(),
                entity.getMainEmail()
        );
    }

    default EmailEntity toEntity(Email domain) {
        if (domain == null) return null;
        EmailEntity entity = new EmailEntity();
        entity.setEmail(domain.getEmail());
        entity.setMainEmail(domain.getPrincipal());
        return entity;
    }

    default List<Email> toDomainEmails(List<EmailEntity> entities) {
        return entities == null ? null : entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    default List<EmailEntity> toEntityEmails(List<Email> domains) {
        return domains == null ? null : domains.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default Endereco toDomain(EnderecoEntity entity) {
        if (entity == null) return null;
        return new Endereco(
                entity.getLogradouroName(),
                entity.getLogradouroNumber(),
                entity.getCidade(),
                entity.getUf(),
                Cep.fromString(entity.getCep()),
                entity.getMainLogradouro()
        );
    }

    default EnderecoEntity toEntity(Endereco domain) {
        if (domain == null) return null;
        EnderecoEntity entity = new EnderecoEntity();
        entity.setLogradouroName(domain.getLogradouro());
        entity.setMainLogradouro(domain.getPrincipal());
        entity.setLogradouroNumber(domain.getLogradouroNumero());
        entity.setCep(domain.getCep().toString());
        entity.setCidade(domain.getCidade());
        entity.setUf(domain.getUf());
        return entity;
    }

    default List<Endereco> toDomainEnderecos(List<EnderecoEntity> entities) {
        return entities == null ? null : entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    default List<EnderecoEntity> toEntityEnderecos(List<Endereco> domains) {
        return domains == null ? null : domains.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default Telefone toDomain(TelefoneEntity entity) {
        if (entity == null) return null;
        return new Telefone(
                entity.getDdd(),
                entity.getTelefone(),
                entity.getMainTelefone()
        );
    }

    default TelefoneEntity toEntity(Telefone domain) {
        if (domain == null) return null;
        TelefoneEntity entity = new TelefoneEntity();
        entity.setTelefone(domain.getTelefone());
        entity.setDdd(domain.getDdd());
        entity.setMainTelefone(domain.getPrincipal());
        return entity;
    }

    default List<Telefone> toDomainTelefones(List<TelefoneEntity> entities) {
        return entities == null ? null : entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    default List<TelefoneEntity> toEntityTelefones(List<Telefone> domains) {
        return domains == null ? null : domains.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
