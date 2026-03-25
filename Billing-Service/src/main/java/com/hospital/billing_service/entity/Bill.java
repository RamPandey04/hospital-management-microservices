package com.hospital.billing_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {


    @Id
    @GeneratedValue
    private Long id;

    private Long appointmentId;
    private Double amount;

    private String status;
}
