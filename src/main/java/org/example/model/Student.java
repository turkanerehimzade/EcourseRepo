package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean studentIsActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Course> courses = new HashSet<>();
}
