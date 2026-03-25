package com.hospital.user_service.USER_SERVICE.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.user_service.USER_SERVICE.dto.AuthRequest;
import com.hospital.user_service.USER_SERVICE.dto.RefreshRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthIntegrationTest {

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private ObjectMapper mapper;

    @LocalServerPort
    int port;

    @Test
    void fullAuthFlow() {

        String baseUrl = "http://localhost:" + port;


        AuthRequest req = new AuthRequest();
        req.setUsername("ram");
        req.setPassword("123");

        var registerRes = restTemplate.postForEntity(
                baseUrl + "/auth/register", req, Map.class);

        assertEquals(200, registerRes.getStatusCode().value());

        String refreshToken = (String) registerRes.getBody().get("refreshToken");

        RefreshRequest refreshReq = new RefreshRequest();
        refreshReq.setRefreshToken(refreshToken);

        var refreshRes = restTemplate.postForEntity(
                baseUrl + "/auth/refresh", refreshReq, Map.class);

        assertEquals(200, refreshRes.getStatusCode().value());
        assertNotNull(refreshRes.getBody().get("accessToken"));
    }
}
