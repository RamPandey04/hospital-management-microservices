package com.hospital.billing_service.controller;

import com.hospital.billing_service.dto.BillDTO;
import com.hospital.billing_service.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class BillingController {

    @Autowired
    private BillingService service;

    @PostMapping("/pay/{appointmentId}")
    public BillDTO pay(@PathVariable Long appointmentId) {
        return service.pay(appointmentId);
    }

    @GetMapping("/{appointmentId}")
    public BillDTO get(@PathVariable Long appointmentId) {
        return service.get(appointmentId);
    }
}
