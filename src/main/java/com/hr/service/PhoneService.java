package com.hr.service;

import com.hr.dto.PhoneDTO;
import com.hr.exceptions.ResourceNotFoundException;
import com.hr.mapper.PhoneMapper;
import com.hr.model.Employee;
import com.hr.model.Phone;
import com.hr.repository.EmployeeRepository;
import com.hr.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final EmployeeRepository employeeRepository;


    public PhoneService(PhoneRepository phoneRepository,EmployeeRepository employeeRepository) {
        this.phoneRepository = phoneRepository;
        this.employeeRepository = employeeRepository;
    }


    public PhoneDTO createPhone(PhoneDTO phoneDTO, Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Phone phone = PhoneMapper.toEntity(phoneDTO, employee);

        phoneRepository.save(phone);

        return PhoneMapper.toDTO(phone);
    }


    public PhoneDTO getPhoneById(Long id) {
        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Phone not found"));
        return PhoneMapper.toDTO(phone);
    }

    public PhoneDTO updatePhone(Long id, PhoneDTO phoneDTO) {

        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phone not found"));

        phone.setPhoneNumber(phoneDTO.getPhoneNumber());

        if (phoneDTO.getEmployee() != null && phoneDTO.getEmployee().getId() != null) {
            Employee employee = employeeRepository.findById(phoneDTO.getEmployee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
            phone.setEmployee(employee);
        }

        phone = phoneRepository.save(phone);

        return PhoneMapper.toDTO(phone);
    }

    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }
}
