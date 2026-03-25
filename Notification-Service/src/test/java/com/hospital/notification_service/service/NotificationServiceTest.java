package com.hospital.notification_service.service;

import com.hospital.notification_service.repo.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository repo;

    @InjectMocks
    private NotificationService service;

    @Test
    void testProcess_created() {
        service.process("APPOINTMENT_CREATED_1");

        verify(repo, times(1)).save(any());
    }
}
