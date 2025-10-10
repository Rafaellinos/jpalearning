package br.com.rafaellinos.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "telefone")
@Getter
@Setter
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "ddd")
    private String ddd;

    @CreatedDate
    @Column(name = "create_date")
    private Instant createdDate;

    @Column(name = "main_telefone", nullable = true)
    private Boolean mainTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    private PessoaEntity pessoaEntity;
}
