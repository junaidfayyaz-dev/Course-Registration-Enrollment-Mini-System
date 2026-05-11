package com.assignment.courseregistration.repository;

import com.assignment.courseregistration.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
