package com.healthcare.healthcare_patient_management.service;



import com.healthcare.healthcare_patient_management.entity.Patient;
import com.healthcare.healthcare_patient_management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        if (patient != null) {
            patient.setName(patientDetails.getName());
            patient.setEmail(patientDetails.getEmail());
            patient.setPhoneNumber(patientDetails.getPhoneNumber());
            patient.setId(patientDetails.getId());
            patientRepository.save(patient);
        }
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
