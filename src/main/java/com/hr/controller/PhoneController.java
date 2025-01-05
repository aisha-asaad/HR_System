package com.hr.controller;

import com.hr.dto.PhoneDTO;
import com.hr.model.Phone;
import com.hr.service.PhoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phones")
public class PhoneController {

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }
    private final PhoneService phoneService;


//    @GetMapping
//    public ResponseEntity<List<PhoneDTO>> getPhonesByEmployee(@RequestParam Long employeeId) {
//        return ResponseEntity.ok(phoneService.getPhonesByEmployeeId(employeeId));
//    }

    @PostMapping
    public ResponseEntity<PhoneDTO> createPhone(@RequestBody PhoneDTO phoneDTO, @RequestParam Long employeeId) {
        // استدعاء خدمة إنشاء الهاتف وتعيين الموظف
        PhoneDTO createdPhone = phoneService.createPhone(phoneDTO, employeeId);
        return ResponseEntity.ok(createdPhone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDTO> getPhoneById(@PathVariable Long id) {
        PhoneDTO phoneDTO = phoneService.getPhoneById(id);
        return ResponseEntity.ok(phoneDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneDTO> updatePhone(@PathVariable Long id, @RequestBody PhoneDTO phoneDTO) {
        PhoneDTO updatedPhone = phoneService.updatePhone(id, phoneDTO);
        return ResponseEntity.ok(updatedPhone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        phoneService.deletePhone(id);
        return ResponseEntity.noContent().build();
    }
}
