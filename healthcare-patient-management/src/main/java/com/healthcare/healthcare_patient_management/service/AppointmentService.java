package com.healthcare.healthcare_patient_management.service;

import com.healthcare.healthcare_patient_management.entity.Appointment;
import com.healthcare.healthcare_patient_management.entity.Diagnosis;
import com.healthcare.healthcare_patient_management.entity.Medication;
import com.healthcare.healthcare_patient_management.entity.Physician;
import com.healthcare.healthcare_patient_management.repository.AppointmentRepository;
import com.healthcare.healthcare_patient_management.repository.DiagnosisRepository;
import com.healthcare.healthcare_patient_management.repository.MedicationRepository;
import com.healthcare.healthcare_patient_management.repository.PhysicianRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public List<Appointment> searchAppointments(String query, String physicianUsername) {
        Optional<Physician> physicianOpt = physicianRepository.findByUsername(physicianUsername);
        if (physicianOpt.isPresent()) {
            Physician physician = physicianOpt.get();
            if (physician.getType().equals("Attending") || physician.getType().equals("Referring")) {
                return appointmentRepository.fuzzySearch(query);
            }
        }
        return new ArrayList<>();
    }
    @Transactional
    public Appointment createAppointment(Appointment appointment) {
        for (Diagnosis diagnosis : appointment.getDiagnoses()) {
            diagnosis.setAppointment(appointment);
        }
        for (Medication medication : appointment.getMedications()) {
            medication.setAppointment(appointment);
        }
        return appointmentRepository.save(appointment);
    }


    public List<Appointment> getAppointmentsByPhysicianId(Long physicianId) {
        return appointmentRepository.findByPhysicianIdOrderByAppointmentDateTimeDesc(physicianId);
    }




    public Appointment getAppointmentById(Long id) {
       return appointmentRepository.findById(id).orElse(null);
    }

    //public void createAppointment(Appointment appointment) {
    //    appointmentRepository.save(appointment);
   // }

    public Appointment updateAppointment(Long appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = getAppointmentById(appointmentId);
        if (existingAppointment != null) {
            existingAppointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
            existingAppointment.setType(updatedAppointment.getType());
            existingAppointment.setDiagnoses(updatedAppointment.getDiagnoses());
            existingAppointment.setMedications(updatedAppointment.getMedications());
            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentsForPhysician(Long physicianId) {
        return appointmentRepository.findByPhysicianIdOrderByAppointmentDateTimeDesc(physicianId);
    }
    public Appointment createApointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        List<Medication> medications = medicationRepository.findByAppointmentId(appointment.getId());
        appointment.setMedications(medications);
        return appointment;
    }
}
