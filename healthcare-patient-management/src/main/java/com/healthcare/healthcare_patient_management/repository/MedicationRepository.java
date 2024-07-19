package com.healthcare.healthcare_patient_management.repository;

import com.healthcare.healthcare_patient_management.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByAppointmentId(Long appointmentId);

    @Query("SELECT m FROM Medication m WHERE m.time <= CURRENT_TIME AND (m.emailAlert = true OR m.smsAlert = true)")
    List<Medication> findDueNotifications();
}
