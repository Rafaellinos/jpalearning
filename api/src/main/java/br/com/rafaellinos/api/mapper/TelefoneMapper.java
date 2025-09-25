package br.com.rafaellinos.api.mapper;

import br.com.rafaellinos.api.dto.TelefoneDto;
import br.com.rafaellinos.core.domain.Telefone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {

    default Telefone toDomain(TelefoneDto telefoneDto) {
        return new Telefone(
                telefoneDto.ddd(),
                telefoneDto.telefone(),
                telefoneDto.principal()
        );
    }

    default TelefoneDto toDto(Telefone telefone) {
        return new TelefoneDto(
                telefone.getDdd(),
                telefone.getTelefone(),
                telefone.getPrincipal()
        );
    }

    default List<Telefone> toDomainList(List<TelefoneDto> dtoList) {
        return dtoList.stream().map(this::toDomain).toList();
    }

    default List<TelefoneDto> toDtoList(List<Telefone> domainList) {
        return domainList.stream().map(this::toDto).toList();
    }
}
