package com.craftlink.backend.security.services;

import com.craftlink.backend.shared.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final JwtProperties jwtProperties;
    private final JwtEncoderService jwtEncoderService;

    public String getUserEmail(String token) {
        return jwtEncoderService.extractSubject(token, jwtProperties.getSecret());
    }

    public String generateAccessToken(String email) {
        return jwtEncoderService.generateToken(email, jwtProperties.getExpirationTime(), jwtProperties.getSecret());
    }
}
