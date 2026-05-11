package com.assignment.courseregistration.repository;

import com.assignment.courseregistration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
