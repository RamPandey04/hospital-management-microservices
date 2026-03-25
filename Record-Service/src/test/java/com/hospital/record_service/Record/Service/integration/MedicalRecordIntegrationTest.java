package com.hospital.record_service.Record.Service.integration;

import com.hospital.record_service.Record.Service.dto.MedicalRecordDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class MedicalRecordIntegrationTest {


    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    int port;

    @Test
    void fullFlow() {

        String base = "http://localhost:" + port;

        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setPatientId(1L);
        dto.setDoctorId(1L);

        var res = rest.postForEntity(base + "/records", dto, MedicalRecordDTO.class);

        assertEquals(200, res.getStatusCode().value());
    }
}
