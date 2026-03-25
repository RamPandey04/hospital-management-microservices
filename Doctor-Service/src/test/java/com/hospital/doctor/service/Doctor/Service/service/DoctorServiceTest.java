package com.hospital.doctor.service.Doctor.Service.service;

import com.hospital.doctor.service.Doctor.Service.dto.DoctorDTO;
import com.hospital.doctor.service.Doctor.Service.entity.Doctor;
import com.hospital.doctor.service.Doctor.Service.mapper.DoctorMapper;
import com.hospital.doctor.service.Doctor.Service.repository.DoctorRepository;
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
class DoctorServiceTest {

    @Mock
    private DoctorRepository repo;
    @Mock private DoctorMapper mapper;
    @Mock private RedisTemplate<String, Object> redis;

    @InjectMocks
    private DoctorService service;

    @Test
    void testCreate() {
        DoctorDTO dto = new DoctorDTO();
        dto.setName("Dr Ram");

        when(repo.save(any())).thenReturn(new Doctor());
        when(mapper.toDTO(any())).thenReturn(dto);

        DoctorDTO res = service.create(dto);

        assertEquals("Dr Ram", res.getName());
    }
}