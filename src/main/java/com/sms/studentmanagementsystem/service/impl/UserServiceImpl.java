package com.sms.studentmanagementsystem.service.impl;

import com.sms.studentmanagementsystem.dto.request.LoginRequest;
import com.sms.studentmanagementsystem.dto.request.RefreshTokenRequest;
import com.sms.studentmanagementsystem.dto.request.RegisterRequest;
import com.sms.studentmanagementsystem.dto.response.LoginResponse;
import com.sms.studentmanagementsystem.dto.response.RefreshTokenResponse;
import com.sms.studentmanagementsystem.dto.response.RegisterResponse;
import com.sms.studentmanagementsystem.entity.RefreshToken;
import com.sms.studentmanagementsystem.entity.Role;
import com.sms.studentmanagementsystem.entity.User;
import com.sms.studentmanagementsystem.repository.RefreshTokenRepository;
import com.sms.studentmanagementsystem.repository.UserRepository;
import com.sms.studentmanagementsystem.security.JwtService;
import com.sms.studentmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole(Role.USER);

        user.setActive(true);

        User savedUser = userRepository.save(user);

        RegisterResponse response = new RegisterResponse();

        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setActive(savedUser.isActive());

        return response;

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid username or password");
        }

        String accessToken = jwtService.generateAccessToken(user.getUsername());
        String refreshTokenValue = jwtService.generateRefreshToken(user.getUsername());

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(refreshTokenValue);
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(
                java.time.LocalDateTime.now().plusDays(7)
        );

        refreshTokenRepository.save(refreshToken);

        return new LoginResponse(
                accessToken,
                refreshTokenValue,
                "Bearer"
        );

    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if(refreshToken.getExpiryDate().isBefore(
                java.time.LocalDateTime.now()
        )){
            throw new RuntimeException("Refresh token expired");
        }

        String accessToken = jwtService.generateAccessToken(refreshToken.getUser().getUsername());

        return new RefreshTokenResponse(
                accessToken,
                "Bearer"
        );
    }
}
