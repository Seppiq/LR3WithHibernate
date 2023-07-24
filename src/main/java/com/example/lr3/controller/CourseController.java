package com.example.lr3.controller;

import com.example.lr3.exception.ResourceNotFoundException;
import com.example.lr3.model.Course;
import com.example.lr3.model.Student;
import com.example.lr3.service.CourseService;
import com.example.lr3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/course")
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseService courceService;

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Course>> get() {
        return ok(courceService.findAll());
    }

    @PostMapping()
    public ResponseEntity<Void> save(@Validated @RequestBody Course course) {
        courceService.save(course);
        return ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Course course = courceService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not exist with id: " + id));
        courceService.delete(course);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public void enrollStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {

        Course course = courceService.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        Student student = studentService.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        course.enrollStudent(student);
        courceService.save(course);
    }
}
