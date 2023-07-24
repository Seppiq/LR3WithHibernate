package com.example.lr3.controller;

import com.example.lr3.model.Course;
import com.example.lr3.model.Student;
import com.example.lr3.service.CourseService;
import com.example.lr3.service.StudentService;
import com.example.lr3.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetCourses() throws Exception {
        Course course1 = new Course();
        Course course2 = new Course();
        course1.setCourseName("Java");
        course2.setCourseName("C++");

        List<Course> courses = Arrays.asList(course1, course2);

        given(courseService.findAll()).willReturn(courses);

        mockMvc.perform(get("/api/course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].courseName", is(course1.getCourseName())))
                .andExpect(jsonPath("$[1].courseName", is(course2.getCourseName())));
    }

    @Test
    public void testSaveCourse() throws Exception {
        Course course = new Course();
        course.setCourseName("Java");

        mockMvc.perform(post("/api/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(course)))
                .andExpect(status().isOk());

        verify(courseService, times(1)).save(course);
    }

    @Test
    public void testDeleteCourse() throws Exception {
        long courseId = 1L;

        given(courseService.findById(courseId)).willReturn(Optional.of(new Course()));

        mockMvc.perform(delete("/api/course/{id}", courseId))
                .andExpect(status().isNoContent());

        verify(courseService, times(1)).findById(courseId);
        verify(courseService, times(1)).delete(any(Course.class));
    }

    @Test
    public void testEnrollStudentToCourse() throws Exception {
        long courseId = 1L;
        long studentId = 1L;

        Course course = new Course();
        Student student = new Student();

        given(courseService.findById(courseId)).willReturn(Optional.of(course));
        given(studentService.findById(studentId)).willReturn(Optional.of(student));

        mockMvc.perform(put("/api/course/{courseId}/students/{studentId}", courseId, studentId))
                .andExpect(status().isOk());

        verify(courseService, times(1)).findById(courseId);
        verify(studentService, times(1)).findById(studentId);
        verify(courseService, times(1)).save(course);
    }
}