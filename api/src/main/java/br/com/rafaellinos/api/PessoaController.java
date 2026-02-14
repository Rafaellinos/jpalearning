package br.com.rafaellinos.api;

import br.com.rafaellinos.api.dto.*;
import br.com.rafaellinos.api.dto.request.*;
import br.com.rafaellinos.api.mapper.*;
import br.com.rafaellinos.core.domain.*;
import br.com.rafaellinos.core.specification.*;
import br.com.rafaellinos.core.usecase.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDto> updatePessoa(
            @PathVariable UUID id,
            @RequestBody PessoaRequestDto request
    ) {
        Pessoa pessoaAtualizada = pessoaUseCase.update(
                id,
                personMapper.toDomain(request)
        );

        return ResponseEntity.ok(
                personMapper.toDto(pessoaAtualizada)
        );
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
