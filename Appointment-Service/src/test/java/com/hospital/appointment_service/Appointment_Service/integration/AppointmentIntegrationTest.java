package com.hospital.appointment_service.Appointment_Service.integration;

import com.hospital.appointment_service.Appointment_Service.dto.AppointmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentIntegrationTest {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    int port;

    @Test
    void fullFlow() {

        String base = "http://localhost:" + port;

        AppointmentDTO dto = new AppointmentDTO();
        dto.setDoctorId(1L);
        dto.setPatientId(1L);
        dto.setSlot("10-11");

        var res = rest.postForEntity(base + "/appointments", dto, AppointmentDTO.class);

        assertEquals(200, res.getStatusCode().value());
    }
}
