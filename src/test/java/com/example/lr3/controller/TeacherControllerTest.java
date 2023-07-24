package com.example.lr3.controller;

import com.example.lr3.model.TeacherHibernate;
import com.example.lr3.service.TeacherService;
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

@WebMvcTest(value = TeacherController.class)
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    @Test
    public void testGetTeachers() throws Exception {
        TeacherHibernate teacher1 = new TeacherHibernate();
        teacher1.setName("Alex");
        TeacherHibernate teacher2 = new TeacherHibernate();
        teacher2.setName("Roman");

        List<TeacherHibernate> teachers = Arrays.asList(teacher1, teacher2);

        given(teacherService.findAll()).willReturn(teachers);

        mockMvc.perform(get("/api/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Alex")))
                .andExpect(jsonPath("$[1].name", is("Roman")));
    }

    @Test
    public void testSaveTeacher() throws Exception {
        TeacherHibernate teacher = new TeacherHibernate();
        teacher.setName("Alex");

        mockMvc.perform(post("/api/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(teacher)))
                .andExpect(status().isOk());

        verify(teacherService, times(1)).save(teacher);
    }
}