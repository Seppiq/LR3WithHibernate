package com.example.lr3.repository;

import com.example.lr3.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    Optional<Student> findById(Long aLong);

    @EntityGraph(value = "entity-graph")
    @Override
    List<Student> findAll();
}