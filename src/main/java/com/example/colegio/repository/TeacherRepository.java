package com.example.colegio.repository;

import com.example.colegio.model.Enrollment;
import com.example.colegio.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    Teacher findFirstById(Long id);
}
