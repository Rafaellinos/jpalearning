package br.com.rafaellinos.api;

import br.com.rafaellinos.api.dto.PageableDto;
import br.com.rafaellinos.api.dto.PessoaRequestDto;
import br.com.rafaellinos.api.dto.PessoaResponseDto;
import br.com.rafaellinos.api.dto.request.Expand;
import br.com.rafaellinos.api.dto.request.PersonQueryParam;
import br.com.rafaellinos.api.mapper.PersonMapper;
import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.specification.PessoaSpecification;
import br.com.rafaellinos.core.usecase.PessoaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaUseCase pessoaUseCase;
    private final PersonMapper personMapper;

    @PostMapping
    public ResponseEntity<PessoaResponseDto> createPessoa(
            @RequestBody PessoaRequestDto personDto
    ) {
        Pessoa pessoa = pessoaUseCase.save(personMapper.toDomain(personDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(personMapper.toDto(pessoa));
    }

    @GetMapping
    public ResponseEntity<PageableDto<PessoaResponseDto>> getPessoa(
            @Validated PersonQueryParam personQueryParam,
            Pageable pageable
    ) {
        PageableDomain<Pessoa> pessoa = pessoaUseCase
                .getPessoa(
                        PessoaSpecification.builder()
                                .withSobrenome(personQueryParam.sobrenome())
                                .withNome(personQueryParam.nome())
                                .withDocumento(personQueryParam.documento())
                                .withPageNumber(pageable.getPageNumber())
                                .withPageSize(pageable.getPageSize())
                                .withSearchEmail(personQueryParam.expands().contains(Expand.EMAIL))
                                .withSearchTelefone(personQueryParam.expands().contains(Expand.TELEFONE))
                                .withSearchEndereco(personQueryParam.expands().contains(Expand.ENDERECO))
                                .withId(personQueryParam.personId()).build()
                );
        return ResponseEntity.ok(personMapper.pessoaResponseDtoPageableDto(pessoa));
    }

}
