package br.com.rafaellinos.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
@Setter
@Getter
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 14)
    private String documento;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "qualification_id", nullable = false)
    private PessoaQualificationEntity qualification;

    @OneToMany(mappedBy = "pessoaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailEntity> emails = new ArrayList<>();

    @OneToMany(mappedBy = "pessoaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneEntity> telefones = new ArrayList<>();

    @OneToMany(mappedBy = "pessoaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> enderecos = new ArrayList<>();


}
