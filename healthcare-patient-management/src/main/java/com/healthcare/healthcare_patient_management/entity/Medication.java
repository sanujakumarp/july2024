package com.healthcare.healthcare_patient_management.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dose;
    private String route;
    private String frequency;
    private String unit;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean emailAlert;
    private boolean smsAlert;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    private LocalTime time;

    private String patientEmail;
    private String patientPhone;
}