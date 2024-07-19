package com.healthcare.healthcare_patient_management.scheduler;


import com.healthcare.healthcare_patient_management.entity.Medication;
import com.healthcare.healthcare_patient_management.repository.MedicationRepository;
import com.healthcare.healthcare_patient_management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MedicationRepository medicationRepository;

    @Scheduled(fixedRate = 60000) // Check every minute
    public void sendNotifications() {
        List<Medication> medications = medicationRepository.findDueNotifications();

        for (Medication medication : medications) {
            if (medication.isEmailAlert()) {
                String emailText = createEmailText(medication);
                notificationService.sendEmail(medication.getPatientEmail(), "Medication Reminder", emailText);
            }

            if (medication.isSmsAlert()) {
                String smsText = createSmsText(medication);
                notificationService.sendSms(medication.getPatientPhone(), smsText);
            }
        }
    }

    private String createEmailText(Medication medication) {
        return "Reminder: You need to take " + medication.getName() +
                " (" + medication.getDose() + " " + medication.getUnit() + ") via " + medication.getRoute() + " at " + medication.getTime();
    }

    private String createSmsText(Medication medication) {
        return "Reminder: " + medication.getName() + " (" + medication.getDose() +
                " " + medication.getUnit() + ") via " + medication.getRoute() + " at " + medication.getTime();
    }
}

