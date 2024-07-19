package com.healthcare.healthcare_patient_management.controller;


import com.healthcare.healthcare_patient_management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send-sms")
    public String sendSms(@RequestParam String to, @RequestParam String text) {
        notificationService.sendSms(to, text);
        return "SMS sent successfully";
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        notificationService.sendEmail(to, subject, text);
        return "Email sent successfully";
    }
}

