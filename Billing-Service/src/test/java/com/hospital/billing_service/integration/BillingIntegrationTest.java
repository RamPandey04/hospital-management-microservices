package com.hospital.billing_service.integration;

import com.hospital.billing_service.dto.BillDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BillingIntegrationTest {

    @Autowired
    private TestRestTemplate rest;

    @LocalServerPort
    int port;

    @Test
    void fullFlow() {

        String base = "http://localhost:" + port;

        rest.postForEntity(base + "/billing/pay/1", null, BillDTO.class);

        var res = rest.getForEntity(base + "/billing/1", BillDTO.class);

        assertEquals(200, res.getStatusCode().value());
    }
}
