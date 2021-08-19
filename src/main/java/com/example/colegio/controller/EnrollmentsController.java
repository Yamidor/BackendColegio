package com.example.colegio.controller;

import com.example.colegio.dto.EnrollmentDto;
import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.service.EnrollmentService;
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
public class EnrollmentsController {

    private EnrollmentService enrollmentService;

    public EnrollmentsController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(value = "/enrollments/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<EnrollmentDto>> byId(@PathVariable Long id) {
        EnrollmentDto enrollmentDto = enrollmentService.findById(id);
        EntityModel<EnrollmentDto> resource = new EntityModel<>(enrollmentDto);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    // @formatter:off
    @GetMapping(value = "/enrollments", produces = "application/hal+json")
    public ResponseEntity<PagedContentDto<List<EnrollmentDto>>> findAll(
            @RequestParam(required = false) final String student,
            @RequestParam(required = true) Integer currentPage,
            @RequestParam(required = true) Integer sizePage) {

        final HashMap<String, Object> params = new HashMap<>();
        if (student != null) {
            params.put(EnrollmentDto._IDENTIFICATION, student);
        }

        Pageable pageable = PageRequest.of(currentPage, sizePage);

        PagedContentDto<List<EnrollmentDto>> pagedContent = enrollmentService.findFilterPaged(params, pageable);

        return new ResponseEntity<>(pagedContent, HttpStatus.OK);
    }
    // @formatter:on

    @PostMapping(value = "/enrollments", produces = "application/hal+json")
    public ResponseEntity<EnrollmentDto> create(@RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto response = enrollmentService.create(enrollmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/enrollments/{id}", produces = "application/hal+json")
    public ResponseEntity<EnrollmentDto> update(@PathVariable Long id, @RequestBody EnrollmentDto enrollmentDto) {
        enrollmentDto.setId(id);
        EnrollmentDto response = enrollmentService.update(enrollmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/enrollments/{id}", produces = "application/hal+json")
    public ResponseEntity<EnrollmentDto> deleteById(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
