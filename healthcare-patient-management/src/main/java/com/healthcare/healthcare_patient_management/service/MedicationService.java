package com.healthcare.healthcare_patient_management.service;

import com.healthcare.healthcare_patient_management.entity.Appointment;
import com.healthcare.healthcare_patient_management.entity.Medication;
import com.healthcare.healthcare_patient_management.exception.ResourceNotFoundException;
import com.healthcare.healthcare_patient_management.repository.AppointmentRepository;
import com.healthcare.healthcare_patient_management.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Medication createOrUpdateMedication(Medication medication) {
        // Fetch and set Appointment entity
        Appointment appointment = appointmentRepository.findById(medication.getAppointment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        medication.setAppointment(appointment);
        return medicationRepository.save(medication);
    }

    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    public List<Medication> getMedicationsByAppointment(Long appointmentId) {
        return medicationRepository.findByAppointmentId(appointmentId);
    }

    public Medication addMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    public Medication updateMedication(Long id, Medication medication) {
        Medication existingMedication = medicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Medication not found"));
        existingMedication.setName(medication.getName());
        existingMedication.setDose(medication.getDose());
        existingMedication.setRoute(medication.getRoute());
        existingMedication.setFrequency(medication.getFrequency());
        existingMedication.setUnit(medication.getUnit());
        existingMedication.setStartDate(medication.getStartDate());
        existingMedication.setEndDate(medication.getEndDate());
        existingMedication.setEmailAlert(medication.isEmailAlert());
        existingMedication.setSmsAlert(medication.isSmsAlert());
        existingMedication.setTime(medication.getTime());
        existingMedication.setPatientEmail(medication.getPatientEmail());
        existingMedication.setPatientPhone(medication.getPatientPhone());
        return medicationRepository.save(existingMedication);
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
}
