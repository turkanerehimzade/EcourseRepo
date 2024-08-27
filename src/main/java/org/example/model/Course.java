package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class Course {
    private Long id;

    private String courseName;
    private Boolean courseIsActive;
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    private Set<Student> students = new HashSet<>();
}
