package com.example.lr3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSES")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "COURSENAME", nullable = false)
    private String courseName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "courses_attended",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Student> attend = new HashSet<>();

    public void enrollStudent(Student student) {
        attend.add(student);
    }
}
