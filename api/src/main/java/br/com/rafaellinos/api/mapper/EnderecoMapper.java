package br.com.rafaellinos.api.mapper;

import br.com.rafaellinos.api.dto.EnderecoDto;
import br.com.rafaellinos.core.domain.Cep;
import br.com.rafaellinos.core.domain.Endereco;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    default Endereco toDomain(EnderecoDto enderecoDto) {
        return new Endereco(
                enderecoDto.logradouro(),
                enderecoDto.logradouroNumero(),
                enderecoDto.cidade(),
                enderecoDto.uf(),
                Cep.fromString(enderecoDto.cep()),
                enderecoDto.principal()
        );
    }

    default EnderecoDto toDto(Endereco endereco) {
        return new EnderecoDto(
                endereco.getLogradouro(),
                endereco.getLogradouroNumero(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep().toString(),
                endereco.getPrincipal()
        );
    }

    default List<Endereco> toDomainList(List<EnderecoDto> enderecoDtoList) {
        return enderecoDtoList.stream().map(this::toDomain).toList();
    }

    default List<EnderecoDto> toDtoList(List<Endereco> enderecoList) {
        return enderecoList.stream().map(this::toDto).toList();
    }
}
