package com.example.colegio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "parents")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String identificationType;
    @Column(unique = true)
    private String identificationNumber;
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Enrollment> enrollments;
}
