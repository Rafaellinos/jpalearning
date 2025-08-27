package br.com.rafaellinos.repository.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
@Data
public class PersonEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "qualification_id", nullable = false)
    private PersonQualificationEntity qualification;

    // Reverse relationships
    @OneToMany(mappedBy = "teacher")
    private Set<CourseEntity> coursesTaught = new HashSet<>();

    @ManyToMany(mappedBy = "students")
    private Set<CourseEntity> coursesEnrolled = new HashSet<>();

}
