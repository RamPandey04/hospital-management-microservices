package com.hospital.appointment_service.Appointment_Service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppointmentProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(String event) {
        kafkaTemplate.send("appointment-topic", event);
    }
}
