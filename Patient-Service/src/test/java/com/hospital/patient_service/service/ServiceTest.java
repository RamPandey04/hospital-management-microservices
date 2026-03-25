package com.hospital.patient_service.service;

import com.hospital.patient_service.dto.PatientDTO;
import com.hospital.patient_service.entity.Patient;
import com.hospital.patient_service.mapper.PatientMapper;
import com.hospital.patient_service.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository repo;
    @Mock private PatientMapper mapper;
    @Mock private RedisTemplate<String, Object> redis;

    @InjectMocks
    private PatientService service;

    @Test
    void testCreate() {
        PatientDTO dto = new PatientDTO();
        dto.setName("ram");

        when(repo.save(any())).thenReturn(new Patient());
        when(mapper.toDTO(any())).thenReturn(dto);

        PatientDTO res = service.create(dto);

        assertEquals("ram", res.getName());
    }
}
