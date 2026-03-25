package com.hospital.record_service.Record.Service.entity;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue
    private Long id;

    private Long patientId;
    private Long doctorId;
    private Long appointmentId;

    private String diagnosis;
    private String prescription;
    private String notes;
}
