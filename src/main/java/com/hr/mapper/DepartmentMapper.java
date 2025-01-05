package com.hr.mapper;

import com.hr.dto.DepartmentDTO;
import com.hr.model.Department;

import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDTO toDTO(Department department) {
        if (department == null) return null;
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setEmployees(department.getEmployees() == null ? null :
                department.getEmployees().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    public static Department toEntity(DepartmentDTO dto) {
        if (dto == null) return null;
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        return department;
    }
}
