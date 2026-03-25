package com.hospital.billing_service.service;

import com.hospital.billing_service.dto.BillDTO;
import com.hospital.billing_service.entity.Bill;
import com.hospital.billing_service.repo.BillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class BillingServiceTest {

    @Mock
    private BillRepository repo;

    @InjectMocks
    private BillingService service;

    @Test
    void testGenerateBill() {

        service.generateBill(1L);

        verify(repo, times(1)).save(any());
    }

    @Test
    void testPay() {


        Bill bill = new Bill();
        bill.setAppointmentId(1L);
        bill.setStatus("PENDING");


        when(repo.findByAppointmentId(1L))
                .thenReturn(Optional.of(bill));

        BillDTO res = service.pay(1L);

        assertEquals("PAID", res.getStatus());
    }
}
