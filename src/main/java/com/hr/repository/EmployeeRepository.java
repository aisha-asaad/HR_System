package com.hr.repository;

import com.hr.model.Department;
import com.hr.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
        Page<Employee> findByNameContaining(String name, Pageable pageable);
        Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
    }