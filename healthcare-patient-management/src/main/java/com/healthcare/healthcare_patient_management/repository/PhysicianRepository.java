package com.healthcare.healthcare_patient_management.repository;



import com.healthcare.healthcare_patient_management.entity.Physician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    Optional<Physician> findByUsername(String username);
}

