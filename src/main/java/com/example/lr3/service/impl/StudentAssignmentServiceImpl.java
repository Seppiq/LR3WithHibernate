package com.example.lr3.service.impl;

import com.example.lr3.model.StudentAssignment;
import com.example.lr3.repository.StudentAssignmentRepository;
import com.example.lr3.service.StudentAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAssignmentServiceImpl implements StudentAssignmentService {

    @Autowired
    private StudentAssignmentRepository repository;

    @Override
    public List<StudentAssignment> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(StudentAssignment assignment) {
        repository.save(assignment);
    }

    @Override
    public void delete(StudentAssignment assignment) {

    }

    @Override
    public Optional<StudentAssignment> findById(Long aLong) {
        return Optional.empty();
    }

}
