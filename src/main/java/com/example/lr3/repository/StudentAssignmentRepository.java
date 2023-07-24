package com.example.lr3.repository;

import com.example.lr3.model.StudentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAssignmentRepository extends JpaRepository<StudentAssignment, Long> {
    @Override
    Optional<StudentAssignment> findById(Long aLong);

    List<StudentAssignment> findAllByStudentId(Long aLong);
}
