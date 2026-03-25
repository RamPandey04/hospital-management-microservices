package com.hospital.appointment_service.Appointment_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DOCTOR-SERVICE")
public interface DoctorClient {

    @GetMapping("/doctors/{id}/availability")
    Boolean checkAvailability(@PathVariable Long id,
                              @RequestParam String slot);
}
