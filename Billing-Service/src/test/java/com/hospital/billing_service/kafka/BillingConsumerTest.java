package com.hospital.billing_service.kafka;

import com.hospital.billing_service.service.BillingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;

public class BillingConsumerTest {

    @Autowired
    private BillingConsumer consumer;

    @MockitoBean  private BillingService service;

    @Test
    void testConsume() {
        consumer.consume("APPOINTMENT_CREATED_1");

        verify(service).generateBill(1L);
    }
}
