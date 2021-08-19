package com.example.colegio.service;

import com.example.colegio.delegate.StudentDelegate;
import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.generic.NotFoundException;
import com.example.colegio.mapper.StudentMapper;
import com.example.colegio.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class StudentService {
    private StudentDelegate studentDelegade;

    public StudentService(StudentDelegate studentDelegade) {
        this.studentDelegade = studentDelegade;
    }

    public StudentDto findById(Long id) {
        StudentDto studentDto = new StudentDto();
        studentDto = studentDelegade.findById(id);
        if (studentDto == null) {
            throw new NotFoundException();
        }
        return studentDto;
    }

    public StudentDto create(StudentDto studentDto) {
        Student student = StudentMapper.toModel(studentDto);
        student = studentDelegade.create(student);
        studentDto = StudentMapper.toDto(student);
        return studentDto;
    }

    public void deleteById(Long id) {
        studentDelegade.deleteById(id);
    }

    public StudentDto update(StudentDto studentDto) {
        studentDto = studentDelegade.update(studentDto);
        return studentDto;
    }

    public PagedContentDto<List<StudentDto>> findFilterPaged(HashMap<String, Object> params,
                                                             Pageable pageable) {
        return studentDelegade.findFilterPaged(params, pageable);
    }
}
