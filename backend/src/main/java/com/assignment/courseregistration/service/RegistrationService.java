package com.assignment.courseregistration.service;

import com.assignment.courseregistration.dto.RegistrationRequestDTO;
import com.assignment.courseregistration.entity.Course;
import com.assignment.courseregistration.entity.Registration;
import com.assignment.courseregistration.entity.Student;
import com.assignment.courseregistration.exception.BusinessRuleViolationException;
import com.assignment.courseregistration.exception.CourseNotFoundException;
import com.assignment.courseregistration.exception.StudentNotFoundException;
import com.assignment.courseregistration.repository.CourseRepository;
import com.assignment.courseregistration.repository.RegistrationRepository;
import com.assignment.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public Registration registerCourse(RegistrationRequestDTO dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + dto.getStudentId()));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + dto.getCourseId()));

        long activeCourseCount = registrationRepository.findByStudentId(student.getId()).stream()
                .filter(r -> r.getSemester().equals(dto.getSemester()))
                .filter(r -> r.getStatus().equals("REGISTERED"))
                .count();
        if (activeCourseCount >= 5) {
            throw new BusinessRuleViolationException("A student cannot register in more than 5 active courses in a semester");
        }

        long seatsFilled = registrationRepository.findByCourseId(course.getId()).stream()
                .filter(r -> r.getSemester().equals(dto.getSemester()))
                .filter(r -> r.getStatus().equals("REGISTERED"))
                .count();
        if (seatsFilled >= course.getMaxSeats()) {
            throw new BusinessRuleViolationException("Course max seats exceeded for semester " + dto.getSemester());
        }

        if (course.getPrerequisite() != null) {
            Long prerequisiteId = course.getPrerequisite().getId();
            boolean completedPrerequisite = registrationRepository.findByStudentId(student.getId()).stream()
                    .anyMatch(r -> r.getCourse().getId().equals(prerequisiteId) && r.getStatus().equals("COMPLETED"));
            if (!completedPrerequisite) {
                throw new BusinessRuleViolationException("Prerequisite not completed for course " + course.getCode());
            }
        }

        boolean duplicateRegistered = registrationRepository.findByStudentId(student.getId()).stream()
                .anyMatch(r -> r.getCourse().getId().equals(course.getId())
                        && r.getSemester().equals(dto.getSemester())
                        && r.getStatus().equals("REGISTERED"));
        if (duplicateRegistered) {
            throw new BusinessRuleViolationException("Student is already registered in this course for the same semester");
        }

        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);
        registration.setSemester(dto.getSemester());
        registration.setStatus("REGISTERED");
        registration.setGrade(null);
        return registrationRepository.save(registration);
    }

    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    public List<Registration> getByStudent(Long studentId) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + studentId));
        return registrationRepository.findByStudentId(studentId);
    }

    public List<Registration> getByCourse(Long courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
        return registrationRepository.findByCourseId(courseId);
    }
}
