package com.hospital.user_service.USER_SERVICE.service;


import com.hospital.user_service.USER_SERVICE.dto.AuthRequest;
import com.hospital.user_service.USER_SERVICE.dto.AuthResponse;
import com.hospital.user_service.USER_SERVICE.entity.RefreshToken;
import com.hospital.user_service.USER_SERVICE.entity.User;
import com.hospital.user_service.USER_SERVICE.repository.RefreshTokenRepository;
import com.hospital.user_service.USER_SERVICE.repository.UserRepository;
import com.hospital.user_service.USER_SERVICE.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepo;
    @Mock private RefreshTokenRepository refreshRepo;
    @Mock private JwtUtil jwtUtil;
    @Mock private PasswordEncoder encoder;

    @InjectMocks
    private UserService service;

    @Test
    void testRegister_success() {
        AuthRequest req = new AuthRequest();
        req.setUsername("ram");
        req.setPassword("123");

        when(encoder.encode("123")).thenReturn("encoded");
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        when(jwtUtil.generateToken(any(), any(), anyLong())).thenReturn("access");

        AuthResponse res = service.Register(req);

        assertNotNull(res);
        assertEquals("access", res.getAccessToken());
        assertNotNull(res.getRefreshToken());
    }

    @Test
    void testLogin_success() {
        AuthRequest req = new AuthRequest();
        req.setUsername("ram");
        req.setPassword("123");

        User user = new User();
        user.setId(1L);
        user.setUserName("ram");
        user.setPassword("encoded");
        user.setRole("ROLE_PATIENT");

        when(userRepo.findByUsername("ram")).thenReturn(Optional.of(user));
        when(encoder.matches("123", "encoded")).thenReturn(true);
        when(jwtUtil.generateToken(any(), any(), anyLong())).thenReturn("access");

        AuthResponse res = service.login(req);

        assertNotNull(res.getAccessToken());
        assertNotNull(res.getRefreshToken());
    }

    @Test
    void testLogin_invalidPassword() {
        AuthRequest req = new AuthRequest();
        req.setUsername("ram");
        req.setPassword("wrong");

        User user = new User();
        user.setPassword("encoded");

        when(userRepo.findByUsername("ram")).thenReturn(Optional.of(user));
        when(encoder.matches("wrong", "encoded")).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.login(req));
    }

    @Test
    void testRefreshToken_success() {
        String oldToken = "refresh123";

        RefreshToken token = new RefreshToken();
        token.setToken(oldToken);
        token.setUserId(1L);
        token.setExpiryDate(Instant.now().plusSeconds(1000));

        User user = new User();
        user.setId(1L);
        user.setUserName("ram");
        user.setRole("ROLE_PATIENT");

        when(refreshRepo.findByToken(oldToken)).thenReturn(Optional.of(token));
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(any(), any(), anyLong())).thenReturn("newAccess");

        AuthResponse res = service.refreshToken(oldToken);

        assertEquals("newAccess", res.getAccessToken());
        assertNotNull(res.getRefreshToken());

        verify(refreshRepo).delete(token);
    }

    @Test
    void testRefreshToken_expired() {
        RefreshToken token = new RefreshToken();
        token.setExpiryDate(Instant.now().minusSeconds(10));

        when(refreshRepo.findByToken("abc")).thenReturn(Optional.of(token));

        assertThrows(RuntimeException.class,
                () -> service.refreshToken("abc"));
    }
}