package com.hospital.patient_service.mapper;

import com.hospital.patient_service.dto.PatientDTO;
import com.hospital.patient_service.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(PatientDTO dto) {
        Patient p = new Patient();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setAge(dto.getAge());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        return p;
    }

    public PatientDTO toDTO(Patient p) {
        PatientDTO dto = new PatientDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setAge(p.getAge());
        dto.setEmail(p.getEmail());
        dto.setPhone(p.getPhone());
        return dto;
    }
}
