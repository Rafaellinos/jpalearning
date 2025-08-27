package br.com.rafaellinos.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Teacher (must be qualification=teacher, but business logic enforces this)
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private PersonEntity teacher;

    // Many-to-many with students
    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<PersonEntity> students = new HashSet<>();

}
