package com.craftlink.backend.config.security.services;

import com.craftlink.backend.shared.properties.JwtProperties;
import com.craftlink.backend.user.models.UserType;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtProperties jwtProperties;
  private final JwtEncoderService jwtEncoderService;

  public String getUserId(String token) {
    return jwtEncoderService.extractSubject(token, jwtProperties.getSecret());
  }

  public String generateAccessToken(String userId, String email, UserType userType,
      Collection<? extends GrantedAuthority> authorities) {
    return jwtEncoderService.generateToken(
        userId,
        Map.of(
            "email", email,
            "userType", userType,
            "authorities", authorities
        ),
        jwtProperties.getExpirationTime(),
        jwtProperties.getSecret());
  }
}
