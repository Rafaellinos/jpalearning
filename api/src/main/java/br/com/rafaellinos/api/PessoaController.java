package br.com.rafaellinos.api;

import br.com.rafaellinos.api.dto.PageableDto;
import br.com.rafaellinos.api.dto.PessoaRequestDto;
import br.com.rafaellinos.api.dto.PessoaResponseDto;
import br.com.rafaellinos.api.mapper.PersonMapper;
import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.specification.PessoaSpecification;
import br.com.rafaellinos.core.usecase.PessoaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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
            @RequestParam UUID personId
    ) {
        PageableDomain<Pessoa> pessoa = pessoaUseCase
                .getPessoa(PessoaSpecification.builder().withId(personId).build());
        return ResponseEntity.ok(personMapper.pessoaResponseDtoPageableDto(pessoa));
    }

}
