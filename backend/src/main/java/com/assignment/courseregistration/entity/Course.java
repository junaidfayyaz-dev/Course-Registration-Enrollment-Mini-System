package com.assignment.courseregistration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String code;

    @NotBlank
    private String title;

    @Min(1)
    @Max(4)
    private int creditHours;

    @Min(1)
    private int maxSeats;

    @ManyToOne(optional = true)
    @JoinColumn(name = "prerequisite_id")
    private Course prerequisite;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Registration> registrations;
}
