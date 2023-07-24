package com.example.lr3.controller;

import com.example.lr3.model.Student;
import com.example.lr3.model.StudentAssignment;
import com.example.lr3.model.TeacherHibernate;
import com.example.lr3.service.StudentAssignmentService;
import com.example.lr3.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AssigmentController.class)
public class AssigmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentAssignmentService assignmentService;

    @Test
    public void testGetAssignments() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Mark");
        TeacherHibernate teacher = new TeacherHibernate();
        teacher.setId(1L);
        teacher.setName("Alex");
        StudentAssignment assignment1 = new StudentAssignment();
        assignment1.setTeacher(teacher);
        assignment1.setDescription("Math");
        assignment1.setStudent(student);

        List<StudentAssignment> assignments = Arrays.asList(assignment1);

        given(assignmentService.findAll()).willReturn(assignments);

        mockMvc.perform(get("/api/assigment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is(assignment1.getDescription())))
                .andExpect(jsonPath("$[0].score", is(assignment1.getScore())))
                .andExpect(jsonPath("$[0].student.name", is("Mark")))
                .andExpect(jsonPath("$[0].teacher.name", is("Alex")));
    }

    @Test
    public void testSaveAssignment() throws Exception {
        Student student = new Student();
        TeacherHibernate teacher = new TeacherHibernate();
        StudentAssignment assignment = new StudentAssignment();

        mockMvc.perform(post("/api/assigment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(assignment)))
                .andExpect(status().isOk());

        verify(assignmentService, times(1)).save(assignment);
    }
}