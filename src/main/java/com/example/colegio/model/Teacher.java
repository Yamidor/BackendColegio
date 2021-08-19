package com.example.colegio.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {
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
}
