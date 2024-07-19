package com.healthcare.healthcare_patient_management.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String status;
    private String category;
    private String severity;
    private String bodySite;
    private LocalDate onsetDate;
    private LocalDate abatementDate;
    private String stage;
    private String evidence;
    private String note;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
