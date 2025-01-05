package com.hr.dto;

import java.util.List;


public class EmployeeDTO {
    private Long id;
    private String name;
    private Long departmentId;
    private List<PhoneDTO> phones;
    private List<EmailDTO> emails;

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, Long departmentId, List<PhoneDTO> phones, List<EmailDTO> emails) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.phones = phones;
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<EmailDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDTO> emails) {
        this.emails = emails;
    }
}