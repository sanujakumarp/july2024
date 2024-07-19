package com.healthcare.healthcare_patient_management.repository;

import com.healthcare.healthcare_patient_management.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {}