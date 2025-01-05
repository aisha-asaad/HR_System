package com.hr.mapper;

import com.hr.dto.EmployeeDTO;
import com.hr.model.Employee;

import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDepartmentId(employee.getDepartment() == null ? null : employee.getDepartment().getId());
        dto.setPhones(employee.getPhones() == null ? null : employee.getPhones().stream().map(PhoneMapper::toDTO).collect(Collectors.toList()));
        dto.setEmails(employee.getEmails() == null ? null : employee.getEmails().stream().map(EmailMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        return employee;
    }
}