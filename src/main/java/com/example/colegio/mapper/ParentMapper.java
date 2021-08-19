package com.example.colegio.mapper;

import com.example.colegio.dto.ParentDto;
import com.example.colegio.dto.StudentDto;
import com.example.colegio.model.Parent;
import com.example.colegio.model.Student;

import java.util.Objects;

public final class ParentMapper {
    private ParentMapper() {}
    public static ParentDto toDto(Parent parent) {
        ParentDto parentDto = null;
        if (parent != null) {
            parentDto = new ParentDto();
            parentDto.setId(parent.getId());
            parentDto.setIdentificationNumber(parent.getIdentificationNumber());
            parentDto.setIdentificationType(parent.getIdentificationType());
            parentDto.setLastName(parent.getLastName());
            parentDto.setName(parent.getName());
            parentDto.setPhone(parent.getPhone());
        }
        return parentDto;
    }

    public static Parent toModel(ParentDto parentDto) {
        Parent parent = null;
        if (Objects.nonNull(parentDto)) {
            parent = new Parent();
            parent.setId(parentDto.getId());
            parent.setIdentificationNumber(parentDto.getIdentificationNumber());
            parent.setIdentificationType(parentDto.getIdentificationType());
            parent.setLastName(parentDto.getLastName());
            parent.setName(parentDto.getName());
            parent.setPhone(parentDto.getPhone());
        }
        return parent;
    }
}
