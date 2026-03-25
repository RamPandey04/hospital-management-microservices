package com.hospital.notification_service.integration;

import com.hospital.notification_service.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotificationIntegrationTest {

    @Autowired
    private NotificationService service;

    @Test
    void fullFlow() {
        service.process("APPOINTMENT_CREATED_1");
    }
}
