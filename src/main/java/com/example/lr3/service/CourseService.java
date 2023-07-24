package com.example.lr3.service;

import com.example.lr3.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> findAll();

    void save(Course course);

    void delete(Course course);

    Optional<Course> findById(Long aLong);
}
