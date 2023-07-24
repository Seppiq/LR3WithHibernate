package com.example.lr3.service;

import com.example.lr3.model.StudentProfile;

import java.util.List;
import java.util.Optional;

public interface StudentProfileService {

    List<StudentProfile> findAll();

    void save(StudentProfile studentProfile);

    void delete(StudentProfile studentProfile);

    Optional<StudentProfile> findById(Long aLong);
}
