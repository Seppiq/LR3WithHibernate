package com.example.lr3.service;

import com.example.lr3.model.StudentAssignment;

import java.util.List;
import java.util.Optional;

public interface StudentAssignmentService {

    List<StudentAssignment> findAll();

    void save(StudentAssignment assignment);

    void delete(StudentAssignment assignment);

    Optional<StudentAssignment> findById(Long aLong);

}
