package com.example.colegio.mapper;

import com.example.colegio.dto.EnrollmentDto;
import com.example.colegio.model.Enrollment;

import java.util.Objects;


public final class EnrollmentMapper {
    private EnrollmentMapper() {}
    public static EnrollmentDto toDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = null;
        if (enrollment != null) {
            enrollmentDto = new EnrollmentDto();
            enrollmentDto.setId(enrollment.getId());
            enrollmentDto.setState(enrollment.getState());
            if (Objects.nonNull(enrollment.getCourse())) {
                enrollmentDto.setCourse( CourseMapper.toDto(enrollment.getCourse()));
            }
            if (Objects.nonNull(enrollment.getStudent())) {
                enrollmentDto.setStudent( StudentMapper.toDto(enrollment.getStudent()));
            }
            if (Objects.nonNull(enrollment.getParent())) {
                enrollmentDto.setParent( ParentMapper.toDto(enrollment.getParent()));
            }


        }
        return enrollmentDto;
    }

    public static Enrollment toModel(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = null;
        if (Objects.nonNull(enrollmentDto)) {
            enrollment = new Enrollment();
            enrollment.setId(enrollmentDto.getId());
            enrollment.setState(enrollmentDto.getState());
            if (Objects.nonNull(enrollmentDto.getCourse())) {
                enrollment.setCourse( CourseMapper.toModel(enrollmentDto.getCourse()));
            }

            if (Objects.nonNull(enrollmentDto.getStudent())) {
                enrollment.setStudent( StudentMapper.toModel(enrollmentDto.getStudent()));
            }
            if (Objects.nonNull(enrollmentDto.getParent())) {
                enrollment.setParent( ParentMapper.toModel(enrollmentDto.getParent()));
            }
        }
        return enrollment;
    }
}
