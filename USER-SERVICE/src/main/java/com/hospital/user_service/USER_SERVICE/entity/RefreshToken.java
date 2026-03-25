package com.hospital.user_service.USER_SERVICE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.Instant;

@Entity
@Data
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private Long userId;
    private Instant expiryDate;
}
