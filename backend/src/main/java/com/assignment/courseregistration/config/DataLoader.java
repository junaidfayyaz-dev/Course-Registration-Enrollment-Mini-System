package com.assignment.courseregistration.config;

import com.assignment.courseregistration.entity.Course;
import com.assignment.courseregistration.entity.Registration;
import com.assignment.courseregistration.entity.Student;
import com.assignment.courseregistration.repository.CourseRepository;
import com.assignment.courseregistration.repository.RegistrationRepository;
import com.assignment.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    @Override
    public void run(String... args) {
        Student s1 = studentRepository.save(new Student(null, "22-CS-101", "Ali", "ali@gmail.com", "BSCS", 4, null));
        Student s2 = studentRepository.save(new Student(null, "22-CS-102", "Ahmed", "ahmed@gmail.com", "BSCS", 4, null));
        Student s3 = studentRepository.save(new Student(null, "22-CS-103", "Sara", "sara@gmail.com", "BSSE", 5, null));
        Student s4 = studentRepository.save(new Student(null, "22-CS-104", "Hina", "hina@gmail.com", "BSAI", 3, null));
        studentRepository.save(new Student(null, "22-CS-105", "Usman", "usman@gmail.com", "BSCS", 6, null));

        Course c1 = courseRepository.save(new Course(null, "CSC-101", "Programming Fundamentals", 3, 30, null, null));
        Course c2 = courseRepository.save(new Course(null, "CSC-324", "Web Engineering", 3, 30, c1, null));
        Course c3 = courseRepository.save(new Course(null, "CSC-210", "Database Systems", 3, 25, null, null));
        courseRepository.save(new Course(null, "CSC-330", "Artificial Intelligence", 3, 20, null, null));
        courseRepository.save(new Course(null, "CSC-410", "Compiler Construction", 3, 15, c2, null));

        registrationRepository.save(new Registration(null, s1, c1, "Spring 2026", "COMPLETED", "A"));
        registrationRepository.save(new Registration(null, s1, c2, "Spring 2026", "REGISTERED", null));
        registrationRepository.save(new Registration(null, s2, c3, "Spring 2026", "REGISTERED", null));
        registrationRepository.save(new Registration(null, s3, c1, "Fall 2025", "COMPLETED", "B+"));
        registrationRepository.save(new Registration(null, s4, c3, "Spring 2026", "DROPPED", null));
    }
}
