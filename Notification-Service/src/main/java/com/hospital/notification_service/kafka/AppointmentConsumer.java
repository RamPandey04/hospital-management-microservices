package com.hospital.notification_service.kafka;

import com.hospital.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConsumer {
    @Autowired
    private NotificationService service;

    @KafkaListener(topics = "appointment-topic", groupId = "notification-group")
    public void consume(String event) {
        service.process(event);
    }
}
