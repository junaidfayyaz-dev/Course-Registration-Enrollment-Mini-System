package com.assignment.courseregistration.repository;

import com.assignment.courseregistration.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStudentId(Long studentId);
    List<Registration> findByCourseId(Long courseId);
    List<Registration> findBySemester(String semester);
}
