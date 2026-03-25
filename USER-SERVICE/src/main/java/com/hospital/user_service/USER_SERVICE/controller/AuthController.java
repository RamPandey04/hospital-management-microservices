package com.hospital.user_service.USER_SERVICE.controller;

import com.hospital.user_service.USER_SERVICE.dto.AuthRequest;
import com.hospital.user_service.USER_SERVICE.dto.AuthResponse;
import com.hospital.user_service.USER_SERVICE.dto.RefreshRequest;
import com.hospital.user_service.USER_SERVICE.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService service;


    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {
        return service.Register(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return service.login(req);
    }
    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {
        return service.refreshToken(request.getRefreshToken());
    }
}
