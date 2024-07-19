package com.healthcare.healthcare_patient_management.repository;




import com.healthcare.healthcare_patient_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPhysicianIdOrderByAppointmentDateTimeDesc(Long physicianId);
    List<Appointment> findByPhysician_IdOrderByAppointmentDateTimeDesc(Long physicianId);


   @Query("SELECT a FROM Appointment a WHERE " +
          "LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(a.type) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
          "LOWER(a.attendingPhysician.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
         "LOWER(a.referringPhysician.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Appointment> searchByQuery(@Param("query") String query);

    @Query("SELECT a FROM Appointment a WHERE " +
            "LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.type) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.attendingPhysician.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(a.referringPhysician.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Appointment> fuzzySearch(@Param("query") String query);
}
