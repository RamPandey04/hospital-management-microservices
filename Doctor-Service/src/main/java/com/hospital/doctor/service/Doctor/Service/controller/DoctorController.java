package com.hospital.doctor.service.Doctor.Service.controller;

import com.hospital.doctor.service.Doctor.Service.dto.DoctorDTO;
import com.hospital.doctor.service.Doctor.Service.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @PostMapping
    public DoctorDTO create(@Valid @RequestBody DoctorDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public DoctorDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public DoctorDTO update(@PathVariable Long id,
                            @RequestBody DoctorDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}/availability")
    public boolean checkAvailability(@PathVariable Long id,
                                     @RequestParam String slot) {
        return service.isAvailable(id, slot);
    }
}
