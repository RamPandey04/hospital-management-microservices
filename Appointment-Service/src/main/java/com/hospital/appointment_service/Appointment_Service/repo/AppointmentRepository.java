package com.hospital.appointment_service.Appointment_Service.repo;

import com.hospital.appointment_service.Appointment_Service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
