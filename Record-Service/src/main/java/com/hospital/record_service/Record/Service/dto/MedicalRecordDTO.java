package com.hospital.record_service.Record.Service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO
{
    private Long id;

    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    private Long appointmentId;

    private String diagnosis;
    private String prescription;
    private String notes;
}
