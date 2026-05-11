package com.assignment.courseregistration.service;

import com.assignment.courseregistration.entity.Student;
import com.assignment.courseregistration.exception.StudentNotFoundException;
import com.assignment.courseregistration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student student) {
        getById(id);
        student.setId(id);
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        Student existing = getById(id);
        studentRepository.delete(existing);
    }
}
