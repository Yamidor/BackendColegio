package com.example.colegio.controller;

import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.service.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class StudentsController {

    private StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/students/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<StudentDto>> byId(@PathVariable Long id) {
        StudentDto studentDto = studentService.findById(id);
        EntityModel<StudentDto> resource = new EntityModel<>(studentDto);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    // @formatter:off
    @GetMapping(value = "/students", produces = "application/hal+json")
    public ResponseEntity<PagedContentDto<List<StudentDto>>> findAll(
            @RequestParam(required = false) final String identificationNumber,
            @RequestParam(required = true) Integer currentPage,
            @RequestParam(required = true) Integer sizePage) {

        final HashMap<String, Object> params = new HashMap<>();
        if (identificationNumber != null) {
            params.put(StudentDto._IDENTIFICATION_NUMBER, identificationNumber);
        }

        Pageable pageable = PageRequest.of(currentPage, sizePage);

        PagedContentDto<List<StudentDto>> pagedContent = studentService.findFilterPaged(params, pageable);

        return new ResponseEntity<>(pagedContent, HttpStatus.OK);
    }
    // @formatter:on

    @PostMapping(value = "/students", produces = "application/hal+json")
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentDto) {
        StudentDto response = studentService.create(studentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/students/{id}", produces = "application/hal+json")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        studentDto.setId(id);
        StudentDto response = studentService.update(studentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/students/{id}", produces = "application/hal+json")
    public ResponseEntity<StudentDto> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
