package com.hospital.doctor.service.Doctor.Service.repository;

import com.hospital.doctor.service.Doctor.Service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
