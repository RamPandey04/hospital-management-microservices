package com.hospital.appointment_service.Appointment_Service.service;


import com.hospital.appointment_service.Appointment_Service.client.DoctorClient;
import com.hospital.appointment_service.Appointment_Service.dto.AppointmentDTO;
import com.hospital.appointment_service.Appointment_Service.entity.Appointment;
import com.hospital.appointment_service.Appointment_Service.kafka.AppointmentProducer;
import com.hospital.appointment_service.Appointment_Service.repo.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;
    @Autowired private DoctorClient doctorClient;
    @Autowired private AppointmentProducer producer;

    public AppointmentDTO book(AppointmentDTO dto) {

        Boolean available = doctorClient.checkAvailability(
                dto.getDoctorId(), dto.getSlot());

        if (!available) {
            throw new RuntimeException("Doctor not available");
        }

        Appointment a = new Appointment();
        a.setPatientId(dto.getPatientId());
        a.setDoctorId(dto.getDoctorId());
        a.setSlot(dto.getSlot());
        a.setStatus("BOOKED");

        repo.save(a);

        producer.sendEvent("APPOINTMENT_CREATED_" + a.getId());

        dto.setId(a.getId());
        dto.setStatus("BOOKED");

        return dto;
    }

    public void cancel(Long id) {

        Appointment a = repo.findById(id)
                .orElseThrow();

        a.setStatus("CANCELLED");
        repo.save(a);

        producer.sendEvent("APPOINTMENT_CANCELLED_" + id);
    }

    public AppointmentDTO get(Long id) {
        Appointment a = repo.findById(id).orElseThrow();

        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(a.getId());
        dto.setPatientId(a.getPatientId());
        dto.setDoctorId(a.getDoctorId());
        dto.setSlot(a.getSlot());
        dto.setStatus(a.getStatus());

        return dto;
    }
}
