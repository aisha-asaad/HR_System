package com.hr.mapper;

import com.hr.dto.EmailDTO;
import com.hr.model.Email;
import com.hr.model.Employee;
//
//public class EmailMapper {
//    public static Email toEntity(EmailDTO dto, Employee employee) {
//        if (dto == null) return null;
//        Email email = new Email();
//        email.setEmail(dto.getEmail());
//        email.setEmployee(employee);  // ربط الموظف
//        return email;
//    }
//    public static Email toEntity(EmailDTO dto) {
//        Email email = new Email();
//        email.setId(dto.getId());
//        email.setEmail(dto.getEmail());
//        // الحقل employee سيُربط في الخدمة
//        return email;
//    }
//    public static EmailDTO toDTO(Email email) {
//        EmailDTO dto = new EmailDTO();
//        dto.setId(email.getId());
//        dto.setEmail(email.getEmail());
//        dto.setEmployeeId(email.getEmployee() != null ? email.getEmployee().getId() : null);
//        return dto;
//    }
//}
public class EmailMapper {
    public static Email toEntity(EmailDTO dto, Employee employee) {
        if (dto == null) return null;
        Email email = new Email();
        email.setEmail(dto.getEmail());
        email.setEmployee(employee);
        return email;
    }

    public static Email toEntity(EmailDTO dto) {
        Email email = new Email();
        email.setId(dto.getId());
        email.setEmail(dto.getEmail());
        return email;
    }

    public static EmailDTO toDTO(Email email) {
        EmailDTO dto = new EmailDTO();
        dto.setId(email.getId());
        dto.setEmail(email.getEmail());
        dto.setEmployeeId(email.getEmployee() != null ? email.getEmployee().getId() : null);
        return dto;
    }
}