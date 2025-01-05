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

//    public List<PhoneDTO> getPhonesByEmployeeId(Long employeeId) {
//        return phoneRepository.findByEmployeeId(employeeId)
//                .stream()
//                .map(PhoneMapper::toDTO)
//                .toList();
//    }

//    public PhoneDTO getPhoneById(Long id) {
//        Phone phone = phoneRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Phone not found"));
//
//        return PhoneMapper.toDTO(phone);  // تحويل Phone إلى PhoneDTO
//    }

    public PhoneDTO createPhone(PhoneDTO phoneDTO, Long employeeId) {
        // جلب الموظف
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // ربط الهاتف بالموظف
        Phone phone = PhoneMapper.toEntity(phoneDTO, employee);  // هنا بنمرر الـ Employee كمان

        // حفظ الهاتف في قاعدة البيانات
        phoneRepository.save(phone);

        // إرجاع الـ DTO
        return PhoneMapper.toDTO(phone);
    }


    public PhoneDTO getPhoneById(Long id) {
        Phone phone = phoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Phone not found"));
        return PhoneMapper.toDTO(phone);  // تأكد من الـ Mapping بشكل صحيح
    }

    public PhoneDTO updatePhone(Long id, PhoneDTO phoneDTO) {
        // البحث عن الهاتف بواسطة الـ ID
        Phone phone = phoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phone not found"));

        // تحديث الهاتف
        phone.setPhoneNumber(phoneDTO.getPhoneNumber());

        // تعيين الموظف إذا كان موجودًا
        if (phoneDTO.getEmployee() != null && phoneDTO.getEmployee().getId() != null) {
            Employee employee = employeeRepository.findById(phoneDTO.getEmployee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
            phone.setEmployee(employee); // تعيين الموظف
        }

        // حفظ الهاتف المحدث
        phone = phoneRepository.save(phone);

        return PhoneMapper.toDTO(phone);  // إرجاع الهاتف المحدث كـ DTO
    }

    public void deletePhone(Long id) {
        phoneRepository.deleteById(id);
    }
}
