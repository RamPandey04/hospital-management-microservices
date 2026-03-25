package com.hospital.patient_service.Integration;

import com.hospital.patient_service.dto.PatientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    int port;

    @Test
    void testFlow() {

        String base = "http://localhost:" + port;

        PatientDTO dto = new PatientDTO();
        dto.setName("ram");

        var res = rest.postForEntity(base + "/patients", dto, PatientDTO.class);

        assertEquals(200, res.getStatusCode().value());

        Long id = res.getBody().getId();

        var get = rest.getForEntity(base + "/patients/" + id, PatientDTO.class);

        assertEquals(200, get.getStatusCode().value());
    }
}
