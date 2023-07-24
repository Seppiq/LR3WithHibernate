package com.example.lr3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ASSIGMENTS")
@Data
@EqualsAndHashCode(exclude = "student")
@NoArgsConstructor
public class StudentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SCORE")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherHibernate teacher;
}
