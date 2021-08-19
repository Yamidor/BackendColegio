package com.example.colegio.delegate;

import com.example.colegio.dto.EnrollmentDto;
import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.TeacherDto;
import com.example.colegio.mapper.EnrollmentMapper;
import com.example.colegio.mapper.TeacherMapper;
import com.example.colegio.model.Enrollment;
import com.example.colegio.model.Teacher;
import com.example.colegio.repository.EnrollmentRepository;
import com.example.colegio.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TeacherDelegate {

    private TeacherRepository teacherRepository;

    public TeacherDelegate(TeacherRepository teacherRepository){this.teacherRepository = teacherRepository;}

    public TeacherDto findById(Long id) {
        Teacher teacher = teacherRepository.findFirstById(id);
        return TeacherMapper.toDto(teacher);
    }

    public Teacher create(Teacher teacher) {
        teacher = teacherRepository.save(teacher);
        return teacher;
    }

    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    public TeacherDto update(TeacherDto teacherDto) {
        Teacher teacher = TeacherMapper.toModel(teacherDto);
        teacher = teacherRepository.save(teacher);
        return TeacherMapper.toDto(teacher);
    }

    public PagedContentDto<List<TeacherDto>> findFilterPaged(Map<String, Object> params, Pageable pageable) {

        Specification<Teacher> specification = (root, query, criteriaBuilder) -> createFilters(params, root,
                criteriaBuilder);

        Page<Teacher> page = teacherRepository.findAll(specification, pageable);
        List<TeacherDto> teacherDto = page.get().map(TeacherMapper::toDto).collect(Collectors.toList());

        final PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(),
                page.getTotalPages());

        return new PagedContentDto<>(teacherDto, pageMetadata);
    }

    public Predicate createFilters(Map<String, Object> params, Root<Teacher> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (params.get(TeacherDto._IDENTIFICATION_NUMBER) != null) {
            String string = "%".concat(params.get(TeacherDto._IDENTIFICATION_NUMBER).toString()).concat("%");
            predicates.add(criteriaBuilder.like(root.get(TeacherDto._IDENTIFICATION_NUMBER), string));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
    }
}
