package com.example.lr3.service.impl;

import com.example.lr3.model.Course;
import com.example.lr3.repository.CourseRepository;
import com.example.lr3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Optional<Course> findById(Long aLong) {
        return courseRepository.findById(aLong);
    }
}
