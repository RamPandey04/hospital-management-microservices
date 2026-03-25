package com.hospital.record_service.Record.Service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PATIENT-SERVICE")
public interface PatientClient {

    @GetMapping("/patients/{id}")
    Object getPatient(@PathVariable Long id);
}
