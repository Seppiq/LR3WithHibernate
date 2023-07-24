package com.example.lr3.controller;

import com.example.lr3.model.Student;
import com.example.lr3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Student>> get() {
        return ok(studentService.findAll());
    }

    @GetMapping("/avg/{id}")
    public ResponseEntity<Double> getAvg(@PathVariable Long id) {
        return ok(studentService.getAverageScoreForStudent(id));
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody Student student) {
        studentService.save(student);
        return ok().build();
    }
}
