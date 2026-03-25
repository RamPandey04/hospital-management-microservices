package com.hospital.billing_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private Long appointmentId;
    private Double amount;
    private String status;
}
