package com.healthcare.healthcare_patient_management.controller;


import com.healthcare.healthcare_patient_management.entity.Appointment;
import com.healthcare.healthcare_patient_management.entity.Physician;
import com.healthcare.healthcare_patient_management.service.AppointmentService;
import com.healthcare.healthcare_patient_management.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {

    @Autowired
    private PhysicianService physicianService;

    @Autowired
    private AppointmentService appointmentService;

    // Get all physicians
    @GetMapping
    public List<Physician> getAllPhysicians() {
        return physicianService.getAllPhysicians();
    }

    // Get physician by ID
    @GetMapping("/{id}")
    public ResponseEntity<Physician> getPhysicianById(@PathVariable Long id) {
        Physician physician = physicianService.getPhysicianById(id);
        return physician != null ? ResponseEntity.ok(physician) : ResponseEntity.notFound().build();
    }

    // Get all appointments for a specific physician
    @GetMapping("/{physicianId}/appointments")
    public List<Appointment> getAppointmentsByPhysician(@PathVariable Long physicianId) {
        return appointmentService.getAppointmentsForPhysician(physicianId);
    }

    // Create a new physician
    @PostMapping
    public ResponseEntity<Physician> createPhysician(@RequestBody Physician physician) {
        Physician savedPhysician = physicianService.savePhysician(physician);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPhysician);
    }

    // Update an existing physician
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePhysician(@PathVariable Long id, @RequestBody Physician physicianDetails) {
        physicianService.updatePhysician(id, physicianDetails);
        return ResponseEntity.noContent().build();
    }

    // Delete a physician
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhysician(@PathVariable Long id) {
        physicianService.deletePhysician(id);
        return ResponseEntity.noContent().build();
    }
}
