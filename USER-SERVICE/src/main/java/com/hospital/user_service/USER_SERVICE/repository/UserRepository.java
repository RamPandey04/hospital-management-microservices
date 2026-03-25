package com.hospital.user_service.USER_SERVICE.repository;

import com.hospital.user_service.USER_SERVICE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
