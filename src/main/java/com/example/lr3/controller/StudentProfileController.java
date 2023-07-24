package com.example.lr3.controller;

import com.example.lr3.model.StudentProfile;
import com.example.lr3.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping()
    public ResponseEntity<List<StudentProfile>> get() {
        return ok(studentProfileService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody StudentProfile studentProfile) {
        studentProfileService.save(studentProfile);
        return ok().build();
    }
}