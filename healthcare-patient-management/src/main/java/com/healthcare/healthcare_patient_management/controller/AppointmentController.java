package com.healthcare.healthcare_patient_management.controller;


//import com.healthcare.healthcare_patient_management.entity.Appointment;
//import com.healthcare.healthcare_patient_management.entity.Physician;
//import com.healthcare.healthcare_patient_management.service.AppointmentService;
//import com.healthcare.healthcare_patient_management.service.PhysicianService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/appointments")
//public class AppointmentController {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @Autowired
//    private PhysicianService physicianService;
//
////    @GetMapping
////    public List<Appointment> viewAppointments(Principal principal) {
////        Physician physician = physicianService.findByUsername(principal.getName());
////        return appointmentService.getAppointmentsForPhysician(physician);
////    }
//
//    @PutMapping("/{appointmentId}")
//    public ResponseEntity<Appointment> updateAppointment(
//            @PathVariable Long appointmentId,
//            @RequestBody Appointment updatedAppointment) {
//        Appointment appointment = appointmentService.updateAppointment(appointmentId, updatedAppointment);
//        return ResponseEntity.ok(appointment);
//    }
//@GetMapping("/search")
//public ResponseEntity<List<Appointment>> searchAppointments(
//        @RequestParam("query") String query,
//        Principal principal) {
//    String username = principal.getName();
//    List<Appointment> appointments = appointmentService.searchAppointments(query, username);
//    return ResponseEntity.ok(appointments);
//}
//    @GetMapping("/physician/{physicianId}")
//    public List<Appointment> getAppointmentsByPhysician(@PathVariable Long physicianId) {
//        return appointmentService.getAppointmentsByPhysicianId(physicianId);
//    }
//
//    @PostMapping
//    public void createAppointment(@RequestBody Appointment appointment) {
//        appointmentService.createAppointment(appointment);
//    }
//
//    @PutMapping("/{id}")
//    public void updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
//        Appointment appointment = appointmentService.getAppointmentById(id);
//        if (appointment != null) {
//            appointment.setAppointmentDateTime(appointmentDetails.getAppointmentDateTime());
//            appointment.setType(appointmentDetails.getType());
//            appointmentService.updateAppointment(appointment);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAppointment(@PathVariable Long id) {
//        appointmentService.deleteAppointment(id);
//    }
//}
//
import com.healthcare.healthcare_patient_management.entity.Appointment;
import com.healthcare.healthcare_patient_management.entity.Medication;
import com.healthcare.healthcare_patient_management.entity.Physician;
import com.healthcare.healthcare_patient_management.service.AppointmentService;
import com.healthcare.healthcare_patient_management.service.MedicationService;
import com.healthcare.healthcare_patient_management.service.PhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PhysicianService physicianService;
    @Autowired
    private MedicationService medicationService;

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody Appointment updatedAppointment) {
        Appointment appointment = appointmentService.updateAppointment(appointmentId, updatedAppointment);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Appointment>> searchAppointments(
            @RequestParam("query") String query,
            Principal principal) {
        String username = principal.getName();
        List<Appointment> appointments = appointmentService.searchAppointments(query, username);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/physician/{physicianId}")
    public List<Appointment> getAppointmentsByPhysician(@PathVariable Long physicianId) {
        return appointmentService.getAppointmentsByPhysicianId(physicianId);
    }

    @PostMapping("/ram")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
    @PostMapping("/{appointmentId}/medications")
    public Medication createOrUpdateMedication(@PathVariable Long appointmentId, @RequestBody Medication medication) {
        medication.setAppointment(new Appointment());
        medication.getAppointment().setId(appointmentId);
        return medicationService.createOrUpdateMedication(medication);
    }

    @DeleteMapping("/medications/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }

    @GetMapping("/{appointmentId}/medications")
    public List<Medication> getMedications(@PathVariable Long appointmentId) {
        return medicationService.getMedicationsByAppointment(appointmentId);
    }
}

