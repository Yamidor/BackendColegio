package com.example.colegio.repository;

import com.example.colegio.model.Enrollment;
import com.example.colegio.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
    Enrollment findFirstById(Long id);
}
