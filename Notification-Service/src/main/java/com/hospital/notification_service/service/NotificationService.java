package com.hospital.notification_service.service;

import com.hospital.notification_service.entity.Notification;
import com.hospital.notification_service.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repo;

    public void process(String event) {

        String msg;

        if (event.contains("CREATED")) {
            msg = "Appointment booked successfully";
        } else if (event.contains("CANCELLED")) {
            msg = "Appointment cancelled";
        } else {
            msg = "Unknown event";
        }

        Notification n = new Notification();
        n.setMessage(msg);
        n.setType("EMAIL");

        repo.save(n);


        System.out.println("Notification sent: " + msg);
    }
}
