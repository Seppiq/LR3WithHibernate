package com.example.lr3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "PROFILES")
@Data
@ToString(exclude = "student")
@NoArgsConstructor
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private Student student;

    @Column(name = "DOB")
    private LocalDate dob;

    @Column(name = "ADDRESS")
    private String address;
}
