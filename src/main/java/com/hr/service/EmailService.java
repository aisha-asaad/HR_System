package com.hr.service;

import com.hr.dto.EmailDTO;
import com.hr.exceptions.ResourceNotFoundException;
import com.hr.mapper.EmailMapper;
import com.hr.model.Email;
import com.hr.model.Employee;
import com.hr.repository.EmailRepository;
import com.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final EmployeeRepository employeeRepository;

    public EmailService(EmailRepository emailRepository, EmployeeRepository employeeRepository) {
        this.emailRepository = emailRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<EmailDTO> getEmailsByEmployeeId(Long employeeId) {
        return emailRepository.findAll().stream()
                .filter(email -> email.getEmployee() != null && email.getEmployee().getId().equals(employeeId))
                .map(EmailMapper::toDTO)
                .toList();
    }

    public EmailDTO createEmail(EmailDTO dto) {
        // تحويل DTO إلى كيان
        Email email = EmailMapper.toEntity(dto);

        // التحقق من وجود الموظف وربط البريد الإلكتروني به
        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
            email.setEmployee(employee);
        }

        // حفظ البريد الإلكتروني
        return EmailMapper.toDTO(emailRepository.save(email));
    }


    public EmailDTO updateEmail(Long id, EmailDTO dto) {
        // التحقق من وجود البريد الإلكتروني
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));

        // تحديث الحقول
        email.setEmail(dto.getEmail());

        // التحقق من وجود الموظف وربطه بالبريد الإلكتروني
        if (dto.getEmployeeId() != null) {
            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
            email.setEmployee(employee);
        } else {
            throw new IllegalArgumentException("Employee ID is required for updating email.");
        }

        // حفظ البريد الإلكتروني
        return EmailMapper.toDTO(emailRepository.save(email));
    }


    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }
}
