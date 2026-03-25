package com.hospital.billing_service.kafka;

import com.hospital.billing_service.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BillingConsumer {
    @Autowired
    private BillingService service;

    @KafkaListener(topics = "appointment-topic", groupId = "billing-group")
    public void consume(String event) {

        if (event.startsWith("APPOINTMENT_CREATED_")) {

            Long appointmentId = Long.parseLong(
                    event.split("_")[2]);

            service.generateBill(appointmentId);
        }
    }
}
