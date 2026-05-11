package com.assignment.courseregistration.service;

import com.assignment.courseregistration.entity.Course;
import com.assignment.courseregistration.exception.CourseNotFoundException;
import com.assignment.courseregistration.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Long id, Course course) {
        getById(id);
        course.setId(id);
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        Course existing = getById(id);
        courseRepository.delete(existing);
    }
}
