package com.example.lr3.service.impl;

import com.example.lr3.model.Student;
import com.example.lr3.model.StudentAssignment;
import com.example.lr3.repository.StudentAssignmentRepository;
import com.example.lr3.repository.StudentRepository;
import com.example.lr3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAssignmentRepository assignmentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public Optional<Student> findById(Long aLong) {
        return studentRepository.findById(aLong);
    }


    @Override
    public double getAverageScoreForStudent(Long student) {

        //Optional<StudentAssignment> assignments = assignmentRepository.findById(student);
        List<StudentAssignment> assignments = assignmentRepository.findAllByStudentId(student);
        System.out.println(assignments);
        System.out.println(student);

        return assignments.stream()
                .mapToInt(a -> a.getScore())
                .average()
                .orElse(0);
    }
}
