package com.hospital.doctor.service.Doctor.Service.integration;

import com.hospital.doctor.service.Doctor.Service.dto.DoctorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DoctorIntegrationTest {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    int port;

    @Test
    void fullFlow() {

        String base = "http://localhost:" + port;

        DoctorDTO dto = new DoctorDTO();
        dto.setName("Dr Ram");
        dto.setSpecialization("Cardio");
        dto.setAvailableSlots("10-11,11-12");

        var res = rest.postForEntity(base + "/doctors", dto, DoctorDTO.class);

        Long id = res.getBody().getId();

        var check = rest.getForEntity(
                base + "/doctors/" + id + "/availability?slot=10-11",
                Boolean.class);

        assertTrue(check.getBody());
    }
}
