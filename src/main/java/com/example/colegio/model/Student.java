package com.example.colegio.model;

import javax.persistence.*;

import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String identificationType;
    @Column(unique = true)
    private String identificationNumber;
    private String phone;
    private String email;
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Enrollment> enrollments;
}
