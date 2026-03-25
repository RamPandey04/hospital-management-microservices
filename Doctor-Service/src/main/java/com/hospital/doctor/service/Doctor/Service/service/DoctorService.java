package com.hospital.doctor.service.Doctor.Service.service;

import com.hospital.doctor.service.Doctor.Service.dto.DoctorDTO;
import com.hospital.doctor.service.Doctor.Service.entity.Doctor;
import com.hospital.doctor.service.Doctor.Service.mapper.DoctorMapper;
import com.hospital.doctor.service.Doctor.Service.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service

public class DoctorService {

    @Autowired
    private DoctorRepository repo;
    @Autowired private DoctorMapper mapper;
    @Autowired private RedisTemplate<String, Object> redis;

    private final String KEY = "DOCTOR_";

    public DoctorDTO create(DoctorDTO dto) {
        Doctor d = repo.save(mapper.toEntity(dto));
        return mapper.toDTO(d);
    }

    public DoctorDTO get(Long id) {

        String key = KEY + id;

        if (redis.hasKey(key)) {
            return (DoctorDTO) redis.opsForValue().get(key);
        }

        Doctor d = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorDTO dto = mapper.toDTO(d);
        redis.opsForValue().set(key, dto);

        return dto;
    }

    public DoctorDTO update(Long id, DoctorDTO dto) {
        Doctor d = repo.findById(id).orElseThrow();

        d.setName(dto.getName());
        d.setSpecialization(dto.getSpecialization());
        d.setAvailableSlots(dto.getAvailableSlots());

        repo.save(d);

        redis.delete(KEY + id);

        return mapper.toDTO(d);
    }

    public boolean isAvailable(Long doctorId, String slot) {

        Doctor d = repo.findById(doctorId)
                .orElseThrow();

        return d.getAvailableSlots() != null &&
                d.getAvailableSlots().contains(slot);
    }
}