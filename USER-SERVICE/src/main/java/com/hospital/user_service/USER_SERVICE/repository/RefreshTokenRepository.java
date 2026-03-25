package com.hospital.user_service.USER_SERVICE.repository;

import com.hospital.user_service.USER_SERVICE.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken , Long> {

    Optional<RefreshToken> findByToken(String  token);

}
