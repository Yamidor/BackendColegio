package com.example.colegio.controller;

import com.example.colegio.dto.PagedContentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.dto.TeacherDto;
import com.example.colegio.service.StudentService;
import com.example.colegio.service.TeacherService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class TeachersController {

    private TeacherService teacherService;

    public TeachersController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/teachers/{id}", produces = "application/hal+json")
    public ResponseEntity<EntityModel<TeacherDto>> byId(@PathVariable Long id) {
        TeacherDto teacherDto = teacherService.findById(id);
        EntityModel<TeacherDto> resource = new EntityModel<>(teacherDto);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    // @formatter:off
    @GetMapping(value = "/teachers", produces = "application/hal+json")
    public ResponseEntity<PagedContentDto<List<TeacherDto>>> findAll(
            @RequestParam(required = false) final String identificationNumber,
            @RequestParam(required = true) Integer currentPage,
            @RequestParam(required = true) Integer sizePage) {

        final HashMap<String, Object> params = new HashMap<>();
        if (identificationNumber != null) {
            params.put(TeacherDto._IDENTIFICATION_NUMBER, identificationNumber);
        }

        Pageable pageable = PageRequest.of(currentPage, sizePage);

        PagedContentDto<List<TeacherDto>> pagedContent = teacherService.findFilterPaged(params, pageable);

        return new ResponseEntity<>(pagedContent, HttpStatus.OK);
    }
    // @formatter:on

    @PostMapping(value = "/teachers", produces = "application/hal+json")
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherDto teacherDto) {
        TeacherDto response = teacherService.create(teacherDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/teachers/{id}", produces = "application/hal+json")
    public ResponseEntity<TeacherDto> update(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
        teacherDto.setId(id);
        TeacherDto response = teacherService.update(teacherDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/teachers/{id}", produces = "application/hal+json")
    public ResponseEntity<TeacherDto> deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
