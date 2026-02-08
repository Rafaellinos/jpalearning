package br.com.rafaellinos.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person_qualification")
@Getter
@Setter
public class PessoaQualificationEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
