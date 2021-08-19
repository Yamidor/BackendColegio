package com.example.colegio.dto;

import com.example.colegio.model.Course;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
public class EnrollmentDto {
    public static final String _IDENTIFICATION= "student";
    private Long id;
    private String state;
    private CourseDto course;
    private StudentDto student;
    private ParentDto parent;
}
