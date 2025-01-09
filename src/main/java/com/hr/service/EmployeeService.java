package com.hr.service;

import com.hr.dto.EmailDTO;
import com.hr.dto.EmployeeDTO;
import com.hr.dto.PhoneDTO;
import com.hr.exceptions.ResourceNotFoundException;
import com.hr.mapper.EmailMapper;
import com.hr.mapper.EmployeeMapper;
import com.hr.mapper.PhoneMapper;
import com.hr.model.Department;
import com.hr.model.Email;
import com.hr.model.Employee;
import com.hr.model.Phone;
import com.hr.repository.DepartmentRepository;
import com.hr.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Page<EmployeeDTO> getEmployees(String name, Long departmentId, int page, int size, String sortBy, String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Page<Employee> employees;
        if (departmentId != null) {
            employees = employeeRepository.findByDepartmentId(departmentId, pageable);
        } else {
            employees = employeeRepository.findByNameContaining(name, pageable);
        }
        return employees.map(EmployeeMapper::toDTO);
    }

    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = EmployeeMapper.toEntity(dto);

        for (Phone phone : employee.getPhones()) {
            phone.setEmployee(employee);
        }

        for (Email email : employee.getEmails()) {
            email.setEmployee(employee);
        }


        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            employee.setDepartment(department);
        }

        if (dto.getPhones() != null) {
            List<Phone> phones = new ArrayList<>();
            for (PhoneDTO phoneDTO : dto.getPhones()) {
                phones.add(PhoneMapper.toEntity(phoneDTO, employee));
            }
            employee.setPhones(phones);
        }

        if (dto.getEmails() != null) {
            List<Email> emails = new ArrayList<>();
            for (EmailDTO emailDTO : dto.getEmails()) {
                emails.add(EmailMapper.toEntity(emailDTO, employee));
            }
            employee.setEmails(emails);
        }

        employee=employeeRepository.save(employee);

        return EmployeeMapper.toDTO(employee);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee.setName(dto.getName());
        return EmployeeMapper.toDTO(employeeRepository.save(employee));
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return EmployeeMapper.toDTO(employee);
    }
}
