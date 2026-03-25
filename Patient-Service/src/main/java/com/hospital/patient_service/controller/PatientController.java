package com.hospital.patient_service.controller;

import com.hospital.patient_service.dto.PatientDTO;
import com.hospital.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public PatientDTO create(@Valid @RequestBody PatientDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public PatientDTO get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PatientDTO update(@PathVariable Long id,
                             @RequestBody PatientDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
