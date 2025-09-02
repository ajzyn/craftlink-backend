package com.craftlink.backend.config.security.services;

import com.craftlink.backend.shared.properties.JwtProperties;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtProperties jwtProperties;
  private final JwtEncoderService jwtEncoderService;

  public String getUserId(String token) {
    return jwtEncoderService.extractSubject(token, jwtProperties.getSecret());
  }

  public String generateAccessToken(String userId, String email) {
    return jwtEncoderService.generateToken(
        userId,
        Map.of("email", email),
        jwtProperties.getExpirationTime(),
        jwtProperties.getSecret());
  }
}
