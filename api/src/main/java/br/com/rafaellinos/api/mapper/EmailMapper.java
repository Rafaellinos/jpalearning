package br.com.rafaellinos.api.mapper;

import br.com.rafaellinos.api.dto.EmailDto;
import br.com.rafaellinos.core.domain.Email;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    default Email toDomain(EmailDto emailDto) {
        return new Email(emailDto.email(), emailDto.principal());
    }

    default EmailDto toDto(Email email) {
        return new EmailDto(email.getEmail(), email.getPrincipal());
    }

    default List<Email> toDomainList(List<EmailDto> emailDtoList) {
        return emailDtoList.stream().map(this::toDomain).toList();
    }

    default List<EmailDto> toDtoList(List<Email> domainList) {
        return domainList.stream().map(this::toDto).toList();
    }
}
