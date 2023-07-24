package com.example.lr3.controller;

import com.example.lr3.model.TeacherHibernate;
import com.example.lr3.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody TeacherHibernate teacher) {
        teacherService.save(teacher);
        return ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<TeacherHibernate>> get() {
        return ok(teacherService.findAll());
    }
}
