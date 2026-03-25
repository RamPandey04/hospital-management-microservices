package com.hospital.patient_service.service;

import com.hospital.patient_service.dto.PatientDTO;
import com.hospital.patient_service.entity.Patient;
import com.hospital.patient_service.mapper.PatientMapper;
import com.hospital.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired private PatientRepository repo;
    @Autowired private PatientMapper mapper;
    @Autowired
    private RedisTemplate<String, Object> redis;

    private final String KEY = "PATIENT_";

    public PatientDTO create(PatientDTO dto) {
        Patient p = repo.save(mapper.toEntity(dto));
        return mapper.toDTO(p);
    }

    public PatientDTO getById(Long id) {

        String key = KEY + id;

        if (redis.hasKey(key)) {
            return (PatientDTO) redis.opsForValue().get(key);
        }

        Patient p = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        PatientDTO dto = mapper.toDTO(p);
        redis.opsForValue().set(key, dto);

        return dto;
    }

    public PatientDTO update(Long id, PatientDTO dto) {
        Patient p = repo.findById(id).orElseThrow();

        p.setName(dto.getName());
        p.setAge(dto.getAge());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());

        repo.save(p);

        redis.delete(KEY + id);

        return mapper.toDTO(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
        redis.delete(KEY + id);
    }
}
