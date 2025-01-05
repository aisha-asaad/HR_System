package com.hr.service;

import com.hr.dto.EmployeeDTO;
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

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService{

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
        // تحويل DTO إلى كيان
        Employee employee = EmployeeMapper.toEntity(dto);

        // ربط القسم (Department)
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            employee.setDepartment(department);
        }

        // ربط الهواتف (Phones)
        if (dto.getPhones() != null) {
            List<Phone> phones = dto.getPhones().stream()
                    .map(phoneDTO -> {
                        Phone phone = PhoneMapper.toEntity(phoneDTO, employee);
                        phone.setEmployee(employee); // ربط الهاتف بالموظف
                        return phone;
                    })
                    .collect(Collectors.toList());
            employee.setPhones(phones);
        }

        // ربط البريد الإلكتروني (Emails)
        if (dto.getEmails() != null) {
            List<Email> emails = dto.getEmails().stream()
                    .map(emailDTO -> {
                        Email email = EmailMapper.toEntity(emailDTO);
                        email.setEmployee(employee); // ربط البريد بالموظف
                        return email;
                    })
                    .collect(Collectors.toList());
            employee.setEmails(emails);
        }

        // حفظ الموظف في قاعدة البيانات
        return EmployeeMapper.toDTO(employeeRepository.save(employee));
    }


    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employee.setName(dto.getName());
        // Update other fields here
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

        return EmployeeMapper.toDTO(employee);  // تحويل الـ Employee إلى EmployeeDTO باستخدام الـ EmployeeMapper
    }
}