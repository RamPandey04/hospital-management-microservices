package com.hospital.appointment_service.Appointment_Service.controller;

import com.hospital.appointment_service.Appointment_Service.dto.AppointmentDTO;
import com.hospital.appointment_service.Appointment_Service.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    public AppointmentDTO book(@Valid @RequestBody AppointmentDTO dto) {
        return service.book(dto);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable Long id) {
        service.cancel(id);
    }

    @GetMapping("/{id}")
    public AppointmentDTO get(@PathVariable Long id) {
        return service.get(id);
    }
}
