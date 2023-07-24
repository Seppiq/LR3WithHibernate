package com.example.lr3.controller;

import com.example.lr3.model.StudentAssignment;
import com.example.lr3.service.StudentAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/assigment")
@CrossOrigin("*")
public class AssigmentController {

    @Autowired
    private StudentAssignmentService assignmentService;

    @GetMapping()
    public ResponseEntity<List<StudentAssignment>> get() {
        return ok(assignmentService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody StudentAssignment assignment) {
        assignmentService.save(assignment);
        return ok().build();
    }
}
