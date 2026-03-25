package com.hospital.record_service.Record.Service.controller;

import com.hospital.record_service.Record.Service.dto.MedicalRecordDTO;
import com.hospital.record_service.Record.Service.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @PostMapping
    public MedicalRecordDTO create(@Valid @RequestBody MedicalRecordDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecordDTO> get(@PathVariable Long patientId) {
        return service.getByPatient(patientId);
    }
}
