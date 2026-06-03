package com.sms.studentmanagementsystem.controller;

import com.sms.studentmanagementsystem.common.ApiResponse;
import com.sms.studentmanagementsystem.dto.request.LoginRequest;
import com.sms.studentmanagementsystem.dto.request.RefreshTokenRequest;
import com.sms.studentmanagementsystem.dto.request.RegisterRequest;
import com.sms.studentmanagementsystem.dto.response.LoginResponse;
import com.sms.studentmanagementsystem.dto.response.RefreshTokenResponse;
import com.sms.studentmanagementsystem.dto.response.RegisterResponse;
import com.sms.studentmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;


    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = userService.register(request);
        return new ApiResponse<>(
                true,
                "User registered successfully",
                response
        );
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response =
                userService.login(request);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }

    @PostMapping("/refresh-token")
    public ApiResponse<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        RefreshTokenResponse response =
                userService.refreshToken(request);

        return new ApiResponse<>(
                true,
                "Access token generated successfully",
                response
        );
    }
}
