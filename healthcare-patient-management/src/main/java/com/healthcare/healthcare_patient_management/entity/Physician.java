package com.healthcare.healthcare_patient_management.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "physician")
public class Physician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    //private String username;
    private String name;
    private String type;

    private String specialty;

    @OneToMany(mappedBy = "physician")
    private List<Appointment> appointments;

}
