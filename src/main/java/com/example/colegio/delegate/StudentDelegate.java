package com.example.colegio.delegate;

import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.mapper.StudentMapper;
import com.example.colegio.model.Student;
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
public class StudentDelegate {

    private StudentRepository studentRepository;

    public StudentDelegate(StudentRepository studentRepository){this.studentRepository = studentRepository;}

    public StudentDto findById(Long id) {
        Student student = studentRepository.findFirstById(id);
        return StudentMapper.toDto(student);
    }

    public Student create(Student student) {
        student = studentRepository.save(student);
        return student;
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public StudentDto update(StudentDto studentDto) {
        Student student = StudentMapper.toModel(studentDto);
        student = studentRepository.save(student);
        return StudentMapper.toDto(student);
    }

    public PagedContentDto<List<StudentDto>> findFilterPaged(Map<String, Object> params, Pageable pageable) {

        Specification<Student> specification = (root, query, criteriaBuilder) -> createFilters(params, root,
                criteriaBuilder);

        Page<Student> page = studentRepository.findAll(specification, pageable);
        List<StudentDto> studentDto = page.get().map(StudentMapper::toDto).collect(Collectors.toList());

        final PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(),
                page.getTotalPages());

        return new PagedContentDto<>(studentDto, pageMetadata);
    }

    public Predicate createFilters(Map<String, Object> params, Root<Student> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (params.get(StudentDto._IDENTIFICATION_NUMBER) != null) {
            String string = "%".concat(params.get(StudentDto._IDENTIFICATION_NUMBER).toString()).concat("%");
            predicates.add(criteriaBuilder.like(root.get(StudentDto._IDENTIFICATION_NUMBER), string));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
    }
}
