package com.example.lr3.service.impl;

import com.example.lr3.model.StudentAssignment;
import com.example.lr3.repository.StudentAssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentAssignmentServiceImplTest {
    @InjectMocks
    private StudentAssignmentServiceImpl studentAssignmentService;

    @Mock
    private StudentAssignmentRepository studentAssignmentRepository;

    @Test
    public void testFindAll() {
        StudentAssignment assignment1 = new StudentAssignment();
        assignment1.setScore(10);
        StudentAssignment assignment2 = new StudentAssignment();
        List<StudentAssignment> assignments = Arrays.asList(assignment1, assignment2);

        when(studentAssignmentRepository.findAll()).thenReturn(assignments);

        List<StudentAssignment> result = studentAssignmentService.findAll();

        assertEquals(2, result.size());
        assertEquals(assignment1, result.get(0));
        assertEquals(assignment2, result.get(1));
    }

    @Test
    public void testSave() {

        StudentAssignment assignment = new StudentAssignment();

        studentAssignmentService.save(assignment);

        verify(studentAssignmentRepository).save(assignment);
    }
}