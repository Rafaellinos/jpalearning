package br.com.rafaellinos.api.mapper;

import br.com.rafaellinos.api.dto.PageableDto;
import br.com.rafaellinos.api.dto.PessoaRequestDto;
import br.com.rafaellinos.api.dto.PessoaResponseDto;
import br.com.rafaellinos.core.domain.Documento;
import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    @Autowired
    protected EmailMapper emailMapper;

    @Autowired
    protected TelefoneMapper telefoneMapper;

    @Autowired
    protected EnderecoMapper enderecoMapper;

    public Pessoa toDomain(PessoaRequestDto pessoaRequestDto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setDocumento(Documento.fromString(pessoaRequestDto.documento()));
        pessoa.setNome(pessoaRequestDto.nome());
        pessoa.setIdade(pessoaRequestDto.idade());
        pessoa.setEmails(emailMapper.toDomainList(pessoaRequestDto.emails()));
        pessoa.setTelefones(telefoneMapper.toDomainList(pessoaRequestDto.telefones()));
        pessoa.setEnderecos(enderecoMapper.toDomainList(pessoaRequestDto.enderecos()));
        return pessoa;
    }

    public PessoaResponseDto toDto(Pessoa pessoa) {
        return new PessoaResponseDto(
                pessoa.getPessoaId(),
                pessoa.getNome(),
                pessoa.getIdade(),
                emailMapper.toDtoList(pessoa.getEmails()),
                telefoneMapper.toDtoList(pessoa.getTelefones()),
                enderecoMapper.toDtoList(pessoa.getEnderecos())
        );
    }

    public PageableDto<PessoaResponseDto> pessoaResponseDtoPageableDto(PageableDomain<Pessoa> pessoaPageableDomain) {
        return new PageableDto<>(
                pessoaPageableDomain.getContent().stream().map(this::toDto).toList(),
                pessoaPageableDomain.getTotalPages(),
                pessoaPageableDomain.getTotalElements()
        );
    }
}
