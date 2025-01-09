package com.hr.mapper;

import com.hr.dto.PhoneDTO;
import com.hr.model.Employee;
import com.hr.model.Phone;

public class PhoneMapper {

    public static Phone toEntity(PhoneDTO dto, Employee employee) {
        if (dto == null) return null;
        Phone phone = new Phone();
        phone.setPhoneNumber(dto.getPhoneNumber());
        phone.setEmployee(employee);
        return phone;
    }

    public static PhoneDTO toDTO(Phone phone) {
        if (phone == null) return null;

        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setId(phone.getId());
        phoneDTO.setPhoneNumber(phone.getPhoneNumber());
        phoneDTO.setEmployeeId(phone.getEmployee().getId());

        return phoneDTO;
    }
}