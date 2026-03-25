package com.hospital.notification_service.repo;

import com.hospital.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,  Long> {
}
