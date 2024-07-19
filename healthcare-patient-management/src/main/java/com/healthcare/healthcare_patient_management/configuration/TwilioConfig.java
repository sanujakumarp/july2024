package com.healthcare.healthcare_patient_management.configuration;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.twilio.Twilio;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Bean
    public TwilioInitializer twilioInit() {
        Twilio.init(accountSid, authToken);
        return new TwilioInitializer();
    }

    public static class TwilioInitializer {
        // This class can be empty or contain configuration related to Twilio
    }
}
