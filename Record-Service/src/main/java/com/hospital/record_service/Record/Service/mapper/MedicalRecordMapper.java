package com.hospital.record_service.Record.Service.mapper;

import com.hospital.record_service.Record.Service.dto.MedicalRecordDTO;
import com.hospital.record_service.Record.Service.entity.MedicalRecord;

public class MedicalRecordMapper {
    public MedicalRecord toEntity(MedicalRecordDTO dto) {
        MedicalRecord r = new MedicalRecord();
        r.setId(dto.getId());
        r.setPatientId(dto.getPatientId());
        r.setDoctorId(dto.getDoctorId());
        r.setAppointmentId(dto.getAppointmentId());
        r.setDiagnosis(dto.getDiagnosis());
        r.setPrescription(dto.getPrescription());
        r.setNotes(dto.getNotes());
        return r;
    }

    public MedicalRecordDTO toDTO(MedicalRecord r) {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setId(r.getId());
        dto.setPatientId(r.getPatientId());
        dto.setDoctorId(r.getDoctorId());
        dto.setAppointmentId(r.getAppointmentId());
        dto.setDiagnosis(r.getDiagnosis());
        dto.setPrescription(r.getPrescription());
        dto.setNotes(r.getNotes());
        return dto;
    }
}
