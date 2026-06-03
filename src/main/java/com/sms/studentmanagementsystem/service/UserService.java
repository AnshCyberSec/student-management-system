package com.sms.studentmanagementsystem.service;

import com.sms.studentmanagementsystem.dto.request.LoginRequest;
import com.sms.studentmanagementsystem.dto.request.RefreshTokenRequest;
import com.sms.studentmanagementsystem.dto.request.RegisterRequest;
import com.sms.studentmanagementsystem.dto.response.LoginResponse;
import com.sms.studentmanagementsystem.dto.response.RefreshTokenResponse;
import com.sms.studentmanagementsystem.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
