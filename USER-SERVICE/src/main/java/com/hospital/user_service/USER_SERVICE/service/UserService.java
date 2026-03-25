package com.hospital.user_service.USER_SERVICE.service;

import com.hospital.user_service.USER_SERVICE.dto.AuthRequest;
import com.hospital.user_service.USER_SERVICE.dto.AuthResponse;
import com.hospital.user_service.USER_SERVICE.entity.RefreshToken;
import com.hospital.user_service.USER_SERVICE.entity.User;
import com.hospital.user_service.USER_SERVICE.repository.RefreshTokenRepository;
import com.hospital.user_service.USER_SERVICE.repository.UserRepository;
import com.hospital.user_service.USER_SERVICE.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private  final JwtUtil util;
    private  final PasswordEncoder encoder;

    public AuthResponse Register(AuthRequest request){

        User user = new User();
        user.setUserName(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("ROLE_PATIENT");

        userRepository.save(user);

        return generateTokens(user);

    }

    private AuthResponse generateTokens(User user) {
        String access = util.generateToken(user.getUserName(), user.getRole(), 900000L);
        String refresh = UUID.randomUUID().toString();

        RefreshToken rt = new RefreshToken();
        rt.setToken(refresh);
        rt.setUserId(user.getId());
        rt.setExpiryDate(Instant.now().plusSeconds(604800));

        refreshTokenRepository.save(rt);

        AuthResponse res = new AuthResponse();
        res.setAccessToken(access);
        res.setRefreshToken(refresh);

        return res;
    }

    public AuthResponse login(AuthRequest req) {
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow();

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateTokens(user);
    }


    public AuthResponse refreshToken(String refreshToken) {

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }

        User user = userRepository.findById(token.getUserId())
                .orElseThrow();

        refreshTokenRepository.delete(token);

        String newRefresh = UUID.randomUUID().toString();

        RefreshToken newToken = new RefreshToken();
        newToken.setToken(newRefresh);
        newToken.setUserId(user.getId());
        newToken.setExpiryDate(Instant.now().plusSeconds(604800));

        refreshTokenRepository.save(newToken);

        String newAccess = util.generateToken(
                user.getUserName(),
                user.getRole(),
                900000L
        );

        AuthResponse res = new AuthResponse();
        res.setAccessToken(newAccess);
        res.setRefreshToken(newRefresh);

        return res;
    }


}
