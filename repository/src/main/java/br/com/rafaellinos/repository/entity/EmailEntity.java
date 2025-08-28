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
@Table(name = "email")
@Data
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "main_email", nullable = true)
    private Boolean mainEmail;

    @CreatedDate
    @Column(name = "create_date")
    private Instant createdDate;
}
