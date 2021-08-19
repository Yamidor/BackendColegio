package com.example.colegio.service;

import com.example.colegio.delegate.StudentDelegate;
import com.example.colegio.delegate.TeacherDelegate;
import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.dto.TeacherDto;
import com.example.colegio.generic.NotFoundException;
import com.example.colegio.mapper.StudentMapper;
import com.example.colegio.mapper.TeacherMapper;
import com.example.colegio.model.Student;
import com.example.colegio.model.Teacher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class TeacherService {
    private TeacherDelegate teacherDelegade;

    public TeacherService(TeacherDelegate teacherDelegade) {
        this.teacherDelegade = teacherDelegade;
    }

    public TeacherDto findById(Long id) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto = teacherDelegade.findById(id);
        if (teacherDto == null) {
            throw new NotFoundException();
        }
        return teacherDto;
    }

    public TeacherDto create(TeacherDto teacherDto) {
        Teacher teacher = TeacherMapper.toModel(teacherDto);
        teacher = teacherDelegade.create(teacher);
        teacherDto = TeacherMapper.toDto(teacher);
        return teacherDto;
    }

    public void deleteById(Long id) {
        teacherDelegade.deleteById(id);
    }

    public TeacherDto update(TeacherDto teacherDto) {
        teacherDto = teacherDelegade.update(teacherDto);
        return teacherDto;
    }

    public PagedContentDto<List<TeacherDto>> findFilterPaged(HashMap<String, Object> params,
                                                             Pageable pageable) {
        return teacherDelegade.findFilterPaged(params, pageable);
    }
}
