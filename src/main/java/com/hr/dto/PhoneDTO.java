package com.hr.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneDTO {
    private Long id;
    private String phoneNumber;
    private Long employeeId;

    @JsonIgnore
    @JsonProperty("employee")  // لو عايزة تمسحيه من الاستجابة
    private EmployeeDTO employee;  // تأكد من وجود الحقل ده

    public PhoneDTO() {
    }

    public PhoneDTO(Long id, String phoneNumber, Long employeeId, EmployeeDTO employee) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.employeeId = employeeId;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }
}

