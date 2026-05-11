package com.assignment.courseregistration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String rollNo;

    @NotBlank
    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @NotBlank
    private String program;

    @Min(1)
    @Max(8)
    private int semester;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Registration> registrations;
}
