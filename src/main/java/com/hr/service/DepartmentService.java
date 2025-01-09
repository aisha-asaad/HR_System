package com.hr.service;


import com.hr.dto.DepartmentDTO;
import com.hr.exceptions.ResourceNotFoundException;
import com.hr.mapper.DepartmentMapper;
import com.hr.model.Department;
import com.hr.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Constructor-based dependency injection
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Page<DepartmentDTO> getDepartments(String name, int page, int size, String sortBy, String direction, boolean includeEmployees) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy));
        Page<Department> departments = departmentRepository.findByNameContaining(name, pageable);

        return departments.map(department -> DepartmentMapper.toDTO(department));
    }


    public DepartmentDTO createDepartment(DepartmentDTO dto) {
            Department department = DepartmentMapper.toEntity(dto);
            return DepartmentMapper.toDTO(departmentRepository.save(department));
        }

        public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
            Department department = departmentRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
            department.setName(dto.getName());
            return DepartmentMapper.toDTO(departmentRepository.save(department));
        }

        public void deleteDepartment(Long id) {
            departmentRepository.deleteById(id);
        }
    }






