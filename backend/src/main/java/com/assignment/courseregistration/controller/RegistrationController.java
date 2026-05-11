package com.assignment.courseregistration.controller;

import com.assignment.courseregistration.dto.RegistrationRequestDTO;
import com.assignment.courseregistration.entity.Registration;
import com.assignment.courseregistration.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration register(@Valid @RequestBody RegistrationRequestDTO requestDTO) {
        return registrationService.registerCourse(requestDTO);
    }

    @GetMapping("/registrations")
    public List<Registration> getAllRegistrations() {
        return registrationService.getAll();
    }

    @GetMapping("/students/{id}/registrations")
    public List<Registration> getStudentRegistrations(@PathVariable Long id) {
        return registrationService.getByStudent(id);
    }

    @GetMapping("/courses/{id}/registrations")
    public List<Registration> getCourseRegistrations(@PathVariable Long id) {
        return registrationService.getByCourse(id);
    }
}
