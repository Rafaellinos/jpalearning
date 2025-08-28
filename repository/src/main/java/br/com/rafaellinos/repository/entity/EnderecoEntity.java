package br.com.rafaellinos.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "endereco")
@Data
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "logradouro_name", nullable = false, length = 100)
    private String logradouroName;

    @Column(name = "main_logradouro", nullable = true)
    private Boolean mainLogradouro;

    @Column(name = "logradouro_number")
    private String logradouroNumber;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @CreatedDate
    @Column(name = "create_date")
    private Instant createdDate;
}
