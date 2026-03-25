package com.hospital.billing_service.service;

import com.hospital.billing_service.dto.BillDTO;
import com.hospital.billing_service.entity.Bill;
import com.hospital.billing_service.repo.BillRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class BillingService {

     private final BillRepository repo;

     public void generateBill(Long appointmentId) {

        Bill bill = new Bill();
        bill.setAppointmentId(appointmentId);
        bill.setAmount(500.0);
        bill.setStatus("PENDING");

        repo.save(bill);
    }

    public BillDTO pay(Long appointmentId) {

        Bill bill = repo.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        bill.setStatus("PAID");
        repo.save(bill);

        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setAppointmentId(bill.getAppointmentId());
        dto.setAmount(bill.getAmount());
        dto.setStatus(bill.getStatus());

        return dto;
    }

    public BillDTO get(Long appointmentId) {
        Bill bill = repo.findByAppointmentId(appointmentId).orElseThrow();

        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setAppointmentId(bill.getAppointmentId());
        dto.setAmount(bill.getAmount());
        dto.setStatus(bill.getStatus());

        return dto;
    }


}
