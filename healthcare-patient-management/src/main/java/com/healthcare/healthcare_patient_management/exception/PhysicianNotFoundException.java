package com.healthcare.healthcare_patient_management.exception;


public class PhysicianNotFoundException extends RuntimeException {
    public PhysicianNotFoundException(Long id) {
        super("Physician not found with ID: " + id);
    }
}

