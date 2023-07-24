package com.example.lr3.service.impl;

import com.example.lr3.model.Course;
import com.example.lr3.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Test
    public void testFindAll() {
        Course course1 = new Course();
        Course course2 = new Course();
        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.findAll();

        assertEquals(2, result.size());
        assertEquals(course1, result.get(0));
        assertEquals(course2, result.get(1));
    }

    @Test
    public void testSave() {
        Course course = new Course();
        courseService.save(course);
        verify(courseRepository).save(course);
    }

    @Test
    public void testDelete() {
        Course course = new Course();

        courseService.delete(course);

        verify(courseRepository).delete(course);
    }

    @Test
    public void testFindById() {
        Course course = new Course();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Optional<Course> result = courseService.findById(1L);

        assertEquals(Optional.of(course), result);
    }
}