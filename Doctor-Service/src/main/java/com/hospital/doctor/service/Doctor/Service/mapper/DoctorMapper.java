package com.hospital.doctor.service.Doctor.Service.mapper;

import com.hospital.doctor.service.Doctor.Service.dto.DoctorDTO;
import com.hospital.doctor.service.Doctor.Service.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity(DoctorDTO dto) {
        Doctor d = new Doctor();
        d.setId(dto.getId());
        d.setName(dto.getName());
        d.setSpecialization(dto.getSpecialization());
        d.setAvailableSlots(dto.getAvailableSlots());
        return d;
    }

    public DoctorDTO toDTO(Doctor d) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setSpecialization(d.getSpecialization());
        dto.setAvailableSlots(d.getAvailableSlots());
        return dto;
    }
}
