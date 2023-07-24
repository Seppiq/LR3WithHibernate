package com.example.lr3.service;

import com.example.lr3.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> findAll();

    void save(Student student);

    void delete(Student student);

    Optional<Student> findById(Long aLong);

    double getAverageScoreForStudent(Long aLong);
}
