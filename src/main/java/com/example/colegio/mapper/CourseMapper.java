package com.example.colegio.mapper;

import com.example.colegio.dto.CourseDto;
import com.example.colegio.dto.EnrollmentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.model.Course;
import com.example.colegio.model.Enrollment;
import com.example.colegio.model.Student;

import java.util.Objects;

public final class CourseMapper {
    private CourseMapper() {}
    public static CourseDto toDto(Course course) {
        CourseDto courseDto = null;
        if (course != null) {
            courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
        }
        return courseDto;
    }

    public static Course toModel(CourseDto courseDto) {
        Course course = null;
        if (Objects.nonNull(courseDto)) {
            course = new Course();
            course.setId(courseDto.getId());
            course.setName(courseDto.getName());
        }
        return course;
    }
}
