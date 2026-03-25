package com.hospital.appointment_service.Appointment_Service.service;

import com.hospital.appointment_service.Appointment_Service.client.DoctorClient;
import com.hospital.appointment_service.Appointment_Service.dto.AppointmentDTO;
import com.hospital.appointment_service.Appointment_Service.kafka.AppointmentProducer;
import com.hospital.appointment_service.Appointment_Service.repo.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repo;
    @Mock private DoctorClient doctorClient;
    @Mock private AppointmentProducer producer;

    @InjectMocks
    private AppointmentService service;

    @Test
    void testBook_success() {

        AppointmentDTO dto = new AppointmentDTO();
        dto.setDoctorId(1L);
        dto.setPatientId(1L);
        dto.setSlot("10-11");

        when(doctorClient.checkAvailability(1L, "10-11"))
                .thenReturn(true);

        when(repo.save(any())).thenAnswer(i -> i.getArguments()[0]);

        AppointmentDTO res = service.book(dto);

        assertEquals("BOOKED", res.getStatus());
    }
}
