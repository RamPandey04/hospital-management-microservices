package com.hospital.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {

    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    private int age;

    @Email
    private String email;

    private String phone;
}
