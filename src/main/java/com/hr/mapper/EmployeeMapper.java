package com.hr.mapper;

import com.hr.dto.EmailDTO;
import com.hr.dto.EmployeeDTO;
import com.hr.dto.PhoneDTO;
import com.hr.model.Employee;

import java.util.stream.Collectors;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        return employee;
    }

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());

        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
        }

        if (employee.getPhones() != null) {
            dto.setPhones(employee.getPhones().stream()
                    .map(phone -> new PhoneDTO(phone.getId(), phone.getPhoneNumber(), phone.getEmployee() != null ? phone.getEmployee().getId() : null))
                    .collect(Collectors.toList()));
        }

        if (employee.getEmails() != null) {
            dto.setEmails(employee.getEmails().stream()
                    .map(email -> new EmailDTO(email.getId(), email.getEmail(), email.getEmployee() != null ? email.getEmployee().getId() : null))
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}