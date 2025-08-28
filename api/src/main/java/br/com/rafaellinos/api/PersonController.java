package br.com.rafaellinos.api;

import br.com.rafaellinos.api.dto.PersonRequestDto;
import br.com.rafaellinos.api.dto.PersonResponseDto;
import br.com.rafaellinos.repository.entity.PersonEntity;
import br.com.rafaellinos.repository.entity.PersonQualificationEntity;
import br.com.rafaellinos.repository.jparepository.PersonQualificationRepository;
import br.com.rafaellinos.repository.jparepository.PersonRepository;
import lombok.RequiredArgsConstructor;
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
public class PersonController {

    private final PersonRepository personRepository;

    private final PersonQualificationRepository personQualificationRepository;


    @PostMapping
    public ResponseEntity<PersonResponseDto> createPerson(
            @RequestBody PersonRequestDto personDto
    ) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setAge(personDto.age());
        personEntity.setName(personDto.name());
        PersonQualificationEntity personQualificationEntity = new PersonQualificationEntity();
        personQualificationEntity.setId(1L);
        personEntity.setQualification(personQualificationEntity);
        personEntity = personRepository.save(personEntity);
        return ResponseEntity.ok(new PersonResponseDto(personEntity.getId(), personDto.name(), personDto.age()));
    }

    @GetMapping
    public ResponseEntity<PersonResponseDto> getPerson(
            @RequestParam UUID personId
    ) {
        PersonEntity personEntity = personRepository.findById(personId).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(new PersonResponseDto(personEntity.getId(), personEntity.getName(), personEntity.getAge()));
    }

}
