package com.example.lr3.service.impl;

import com.example.lr3.model.Student;
import com.example.lr3.model.StudentAssignment;
import com.example.lr3.repository.StudentAssignmentRepository;
import com.example.lr3.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentAssignmentRepository assignmentRepository;

    @Test
    public void testFindAll() {
        Student student1 = new Student();
        Student student2 = new Student();
        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.findAll();

        assertEquals(2, result.size());
        assertEquals(student1, result.get(0));
        assertEquals(student2, result.get(1));
    }

    @Test
    public void testSave() {
        Student student = new Student();

        studentService.save(student);

        verify(studentRepository).save(student);
    }

    @Test
    public void testFindById() {
        Student student = new Student();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.findById(1L);

        assertEquals(Optional.of(student), result);
    }

    @Test
    public void testGetAverageScoreForStudent() {

        Student student = new Student();
        student.setId(1L);

        StudentAssignment assignment1 = new StudentAssignment();
        assignment1.setScore(10);
        assignment1.setStudent(student);
        StudentAssignment assignment2 = new StudentAssignment();
        assignment2.setStudent(student);
        assignment2.setScore(8);
        List<StudentAssignment> assignments = Arrays.asList(assignment1, assignment2);

        when(assignmentRepository.findAllByStudentId(1L)).thenReturn(assignments);

        double result = studentService.getAverageScoreForStudent(1L);

        assertEquals(9.0, result, 0.001);
    }
}