package com.healthcare.healthcare_patient_management.service;



import com.healthcare.healthcare_patient_management.entity.Physician;
import com.healthcare.healthcare_patient_management.exception.PhysicianNotFoundException;
import com.healthcare.healthcare_patient_management.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhysicianService {

    @Autowired
    private PhysicianRepository physicianRepository;

    public Physician findByUsername(String username) {
        return physicianRepository.findByUsername(username).orElse(null);
    }

    public Physician getPhysicianById(Long id) {
        return physicianRepository.findById(id).orElse(null);
    }

    public List<Physician> getAllPhysicians() {
        return physicianRepository.findAll();
    }

    public Physician savePhysician(Physician physician) {
        return physicianRepository.save(physician);
    }

    public void updatePhysician(Long id, Physician physicianDetails) {
        Physician existingPhysician = physicianRepository.findById(id)
                .orElseThrow(() -> new PhysicianNotFoundException(id));

       //  Update fields
     // existingPhysician.setName(physicianDetails.getName());
      // existingPhysician.setSpecialty(physicianDetails.getSpecialty());
       //existingPhysician.setContact(physicianDetails.getContact());
       // Update other fields as necessary

        physicianRepository.save(existingPhysician);
    }

    public void deletePhysician(Long id) {
        physicianRepository.deleteById(id);
    }
}
