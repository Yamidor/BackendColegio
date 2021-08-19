package com.example.colegio.mapper;

import com.example.colegio.dto.StudentDto;
import com.example.colegio.dto.TeacherDto;
import com.example.colegio.model.Student;
import com.example.colegio.model.Teacher;

import java.util.Objects;

public final class TeacherMapper {
    private TeacherMapper() {}
    public static TeacherDto toDto(Teacher teacher) {
        TeacherDto teacherDto = null;
        if (teacher != null) {
            teacherDto = new TeacherDto();
            teacherDto.setId(teacher.getId());
            teacherDto.setAddress(teacher.getAddress());
            teacherDto.setEmail(teacher.getEmail());
            teacherDto.setIdentificationNumber(teacher.getIdentificationNumber());
            teacherDto.setIdentificationType(teacher.getIdentificationType());
            teacherDto.setLastName(teacher.getLastName());
            teacherDto.setName(teacher.getName());
            teacherDto.setPhone(teacher.getPhone());
            teacherDto.setFullName(teacher.getName()+' '+teacher.getLastName());
        }
        return teacherDto;
    }

    public static Teacher toModel(TeacherDto teacherDto) {
        Teacher teacher = null;
        if (Objects.nonNull(teacherDto)) {
            teacher = new Teacher();
            teacher.setId(teacherDto.getId());
            teacher.setAddress(teacherDto.getAddress());
            teacher.setEmail(teacherDto.getEmail());
            teacher.setIdentificationNumber(teacherDto.getIdentificationNumber());
            teacher.setIdentificationType(teacherDto.getIdentificationType());
            teacher.setLastName(teacherDto.getLastName());
            teacher.setName(teacherDto.getName());
            teacher.setPhone(teacherDto.getPhone());
        }
        return teacher;
    }
}
