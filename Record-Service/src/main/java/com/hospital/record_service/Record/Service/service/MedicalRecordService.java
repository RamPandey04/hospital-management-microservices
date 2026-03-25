package com.hospital.record_service.Record.Service.service;

import com.hospital.record_service.Record.Service.config.PatientClient;
import com.hospital.record_service.Record.Service.dto.MedicalRecordDTO;
import com.hospital.record_service.Record.Service.entity.MedicalRecord;
import com.hospital.record_service.Record.Service.mapper.MedicalRecordMapper;
import com.hospital.record_service.Record.Service.repo.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository repo;
    @Autowired private MedicalRecordMapper mapper;
    @Autowired private PatientClient patientClient;

    public MedicalRecordDTO create(MedicalRecordDTO dto) {

        patientClient.getPatient(dto.getPatientId());

        MedicalRecord r = repo.save(mapper.toEntity(dto));

        return mapper.toDTO(r);
    }

    public List<MedicalRecordDTO> getByPatient(Long patientId) {

        return repo.findByPatientId(patientId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
