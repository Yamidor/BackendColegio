package com.example.colegio.service;

import com.example.colegio.delegate.EnrollmentDelegate;
import com.example.colegio.delegate.StudentDelegate;
import com.example.colegio.dto.EnrollmentDto;
import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.generic.NotFoundException;
import com.example.colegio.mapper.EnrollmentMapper;
import com.example.colegio.mapper.StudentMapper;
import com.example.colegio.model.Enrollment;
import com.example.colegio.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class EnrollmentService {
    private EnrollmentDelegate enrollmentDelegate;

    public EnrollmentService(EnrollmentDelegate enrollmentDelegate) {
        this.enrollmentDelegate = enrollmentDelegate;
    }

    public EnrollmentDto findById(Long id) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto = enrollmentDelegate.findById(id);
        if (enrollmentDto == null) {
            throw new NotFoundException();
        }
        return enrollmentDto;
    }

    public EnrollmentDto create(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = EnrollmentMapper.toModel(enrollmentDto);
        enrollment = enrollmentDelegate.create(enrollment);
        enrollmentDto = EnrollmentMapper.toDto(enrollment);
        return enrollmentDto;
    }

    public void deleteById(Long id) {
        enrollmentDelegate.deleteById(id);
    }

    public EnrollmentDto update(EnrollmentDto enrollmentDto) {
        enrollmentDto = enrollmentDelegate.update(enrollmentDto);
        return enrollmentDto;
    }

    public PagedContentDto<List<EnrollmentDto>> findFilterPaged(HashMap<String, Object> params,
                                                             Pageable pageable) {
        return enrollmentDelegate.findFilterPaged(params, pageable);
    }
}
