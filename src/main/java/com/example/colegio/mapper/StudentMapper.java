package com.example.colegio.mapper;

import com.example.colegio.dto.StudentDto;
import com.example.colegio.model.Student;

import java.util.Objects;

public final class StudentMapper {
    private StudentMapper() {}
    public static StudentDto toDto(Student student) {
        StudentDto studentDto = null;
        if (student != null) {
            studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setAddress(student.getAddress());
            studentDto.setEmail(student.getEmail());
            studentDto.setIdentificationNumber(student.getIdentificationNumber());
            studentDto.setIdentificationType(student.getIdentificationType());
            studentDto.setLastName(student.getLastName());
            studentDto.setName(student.getName());
            studentDto.setPhone(student.getPhone());
            studentDto.setFullName(student.getName()+' '+student.getLastName());
        }
        return studentDto;
    }

    public static Student toModel(StudentDto studentDto) {
        Student student = null;
        if (Objects.nonNull(studentDto)) {
            student = new Student();
            student.setId(studentDto.getId());
            student.setAddress(studentDto.getAddress());
            student.setEmail(studentDto.getEmail());
            student.setIdentificationNumber(studentDto.getIdentificationNumber());
            student.setIdentificationType(studentDto.getIdentificationType());
            student.setLastName(studentDto.getLastName());
            student.setName(studentDto.getName());
            student.setPhone(studentDto.getPhone());
        }
        return student;
    }
}
