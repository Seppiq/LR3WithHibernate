package com.example.lr3.service.impl;

import com.example.lr3.model.StudentProfile;
import com.example.lr3.repository.StudentProfileRepository;
import com.example.lr3.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository repository;

    @Override
    public List<StudentProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(StudentProfile studentProfile) {
        studentProfile.setDob(LocalDate.now());

        repository.save(studentProfile);
    }

    @Override
    public void delete(StudentProfile studentProfile) {

    }

    @Override
    public Optional<StudentProfile> findById(Long aLong) {
        return repository.findById(aLong);
    }


}
