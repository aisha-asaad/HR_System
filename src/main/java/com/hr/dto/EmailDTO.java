package com.hr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO {
    private Long id;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email address is required")
    private String email;
    
    private Long employeeId;

    public EmailDTO() {
    }

    public EmailDTO(Long id, String email, Long employeeId) {
        this.id = id;
        this.email = email;
        this.employeeId = employeeId;
    }

    public @Email(message = "Email should be valid") @NotBlank(message = "Email address is required") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email address is required") String email) {
        this.email = email;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
