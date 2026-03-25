package com.hospital.billing_service.repo;

import com.hospital.billing_service.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByAppointmentId(Long appointmentId);

}
