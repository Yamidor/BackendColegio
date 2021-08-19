package com.example.colegio.delegate;

import com.example.colegio.dto.EnrollmentDto;
import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.ParentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.mapper.EnrollmentMapper;
import com.example.colegio.mapper.StudentMapper;
import com.example.colegio.model.Enrollment;
import com.example.colegio.model.Parent;
import com.example.colegio.model.Student;
import com.example.colegio.repository.EnrollmentRepository;
import com.example.colegio.repository.StudentRepository;
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
public class EnrollmentDelegate {

    private EnrollmentRepository enrollmentRepository;

    public EnrollmentDelegate(EnrollmentRepository enrollmentRepository){this.enrollmentRepository = enrollmentRepository;}

    public EnrollmentDto findById(Long id) {
        Enrollment enrollment = enrollmentRepository.findFirstById(id);
        return EnrollmentMapper.toDto(enrollment);
    }

    public Enrollment create(Enrollment enrollment) {
        enrollment = enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    public EnrollmentDto update(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = EnrollmentMapper.toModel(enrollmentDto);
        enrollment = enrollmentRepository.save(enrollment);
        return EnrollmentMapper.toDto(enrollment);
    }

    public PagedContentDto<List<EnrollmentDto>> findFilterPaged(Map<String, Object> params, Pageable pageable) {

        Specification<Enrollment> specification = (root, query, criteriaBuilder) -> createFilters(params, root,
                criteriaBuilder);

        Page<Enrollment> page = enrollmentRepository.findAll(specification, pageable);
        List<EnrollmentDto> enrollmentDto = page.get().map(EnrollmentMapper::toDto).collect(Collectors.toList());

        final PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(),
                page.getTotalPages());

        return new PagedContentDto<>(enrollmentDto, pageMetadata);
    }

    public Predicate createFilters(Map<String, Object> params, Root<Enrollment> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (params.get(EnrollmentDto._IDENTIFICATION) != null) {
            String string = "%".concat(params.get(EnrollmentDto._IDENTIFICATION).toString()).concat("%");
            predicates.add(criteriaBuilder.like(root.get(EnrollmentDto._IDENTIFICATION), string));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
    }
}
