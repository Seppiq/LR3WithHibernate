package com.example.lr3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NamedEntityGraphs({
        @NamedEntityGraph(name = "entity-graph",
                attributeNodes = {@NamedAttributeNode("profile")})
})
@Entity
@Table(name = "STUDENTS")
@Data
@EqualsAndHashCode(exclude = "profile")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private StudentProfile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentAssignment> assignments;

    @JsonIgnore
    @ManyToMany(mappedBy = "attend")
    Set<Course> attendedCourses = new HashSet<>();
}
