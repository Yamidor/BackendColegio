package com.example.colegio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ParentDto {
    public static final String _IDENTIFICATION_NUMBER = "identificationNumber";
    private Long id;
    private String name;
    private String lastName;
    private String identificationType;
    private String identificationNumber;
    private String phone;
}
