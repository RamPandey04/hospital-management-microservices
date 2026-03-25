package com.hospital.record_service.Record.Service.service;

import com.hospital.record_service.Record.Service.config.PatientClient;
import com.hospital.record_service.Record.Service.dto.MedicalRecordDTO;
import com.hospital.record_service.Record.Service.entity.MedicalRecord;
import com.hospital.record_service.Record.Service.mapper.MedicalRecordMapper;
import com.hospital.record_service.Record.Service.repo.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository repo;
    @Mock private MedicalRecordMapper mapper;
    @Mock private PatientClient client;

    @InjectMocks
    private MedicalRecordService service;

    @Test
    void testCreate() {

        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setPatientId(1L);

        when(repo.save(any())).thenReturn(new MedicalRecord());
        when(mapper.toDTO(any())).thenReturn(dto);

        MedicalRecordDTO res = service.create(dto);

        assertEquals(1L, res.getPatientId());
    }
}
