package com.hospital.appointment_service.Appointment_Service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    private Long patientId;
    private Long doctorId;

    private String slot;
    private String status; // BOOKED, CANCELLED
}
