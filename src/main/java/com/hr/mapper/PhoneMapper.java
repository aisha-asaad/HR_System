package com.hr.mapper;

import com.hr.dto.EmployeeDTO;
import com.hr.dto.PhoneDTO;
import com.hr.model.Employee;
import com.hr.model.Phone;

public class PhoneMapper {

    public static Phone toEntity(PhoneDTO phoneDTO, Employee employee) {
        if (phoneDTO == null) return null;

        Phone phone = new Phone();
        phone.setPhoneNumber(phoneDTO.getPhoneNumber());
        phone.setEmployee(employee);  // هنا بنربط الهاتف بالموظف

        return phone;
    }


    // تحويل Phone إلى PhoneDTO
    public static PhoneDTO toDTO(Phone phone) {
        if (phone == null) return null;

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phone.getId());
        phoneDTO.setPhoneNumber(phone.getPhoneNumber());
        phoneDTO.setEmployeeId(phone.getEmployee().getId());  // إضافة الـ Employee ID

        return phoneDTO;
    }
}
