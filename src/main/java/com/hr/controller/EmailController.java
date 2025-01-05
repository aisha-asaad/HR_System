package com.hr.controller;

import com.hr.dto.EmailDTO;
import com.hr.model.Email;
import com.hr.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmailDTO>> getEmailsByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(emailService.getEmailsByEmployeeId(employeeId));
    }

    @PostMapping
    public ResponseEntity<EmailDTO> createEmail(@RequestBody EmailDTO emailDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.createEmail(emailDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmailDTO> updateEmail(@PathVariable Long id, @RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(emailService.updateEmail(id, emailDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }
}
