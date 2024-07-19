package com.healthcare.healthcare_patient_management.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "physician_id",nullable = false)
    private Physician physician;

    @ManyToOne
    private Physician attendingPhysician;

    @ManyToOne
    private Physician referringPhysician;

    private LocalDateTime appointmentDateTime;
    private String type;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnosis> diagnoses;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications;

}
