package com.hr.repository;

import com.hr.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Page<Phone> findByPhoneNumberContaining(String number, Pageable pageable);  // Use phoneNumber
}