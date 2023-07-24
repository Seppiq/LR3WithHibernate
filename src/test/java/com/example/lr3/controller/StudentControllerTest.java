package com.example.lr3.controller;

import com.example.lr3.model.Student;
import com.example.lr3.model.StudentAssignment;
import com.example.lr3.service.StudentService;
import com.example.lr3.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetStudents() throws Exception {
        Student student1 = new Student();
        Student student2 = new Student();

        List<Student> students = Arrays.asList(student1, student2);

        given(studentService.findAll()).willReturn(students);

        mockMvc.perform(get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(student1.getName())))
                .andExpect(jsonPath("$[1].name", is(student2.getName())));
    }

    @Test
    public void testGetAverageScoreForStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        StudentAssignment assignment = new StudentAssignment();
        assignment.setScore(8);
        Set set = new HashSet<>();
        set.add(assignment);
        student.setAssignments(set);

        given(studentService.getAverageScoreForStudent(student.getId())).willReturn(8.0);

        mockMvc.perform(get("/api/student/avg/{id}", student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(8.0)));
    }

    @Test
    public void testSaveStudent() throws Exception {
        Student student = new Student();

        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(student)))
                .andExpect(status().isOk());

        verify(studentService, times(1)).save(student);
    }
}