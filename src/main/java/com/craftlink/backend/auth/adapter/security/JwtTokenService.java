package com.craftlink.backend.auth.adapter.security;

import com.craftlink.backend.auth.domain.model.user.vo.UserType;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.custom.ValidationException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.shared.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

  private final JwtProperties jwtProperties;

  public String getUserId(String token) {
    return extractSubject(token, jwtProperties.getSecret());
  }

  public String generateAccessToken(String userId, String email, UserType userType,
      Collection<? extends GrantedAuthority> authorities) {
    return generateToken(
        userId,
        Map.of(
            "email", email,
            "userType", userType,
            "authorities", authorities
        ),
        jwtProperties.getExpirationTime(),
        jwtProperties.getSecret());
  }


  private String generateToken(String subject, Map<String, Object> claims, Long expTime, String secret) {
    if (expTime == null) {
      throw new ValidationException(
          ExceptionCode.MISSING_REQUIRED_FIELD,
          Map.of("expTimeMillis", "Expiration time is required")
      );
    }

    try {
      Date tokenExpTime = Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + expTime));

      return Jwts.builder()
          .setExpiration(tokenExpTime)
          .setClaims(claims)
          .setSubject(subject)
          .setIssuedAt(new Date())
          .signWith(getSigningKey(secret))
          .compact();
    } catch (JwtException e) {
      throw new SecurityException(
          ExceptionCode.JWT_INTERNAL_ERROR,
          "Error generating JWT: " + e.getMessage()
      );
    }
  }

  private String extractSubject(String token, String secret) {
    try {

      Claims claims = Jwts
          .parserBuilder()
          .setSigningKey(getSigningKey(secret))
          .build()
          .parseClaimsJws(token)
          .getBody();

      return claims.getSubject();
    } catch (ExpiredJwtException e) {
      throw new SecurityException(
          ExceptionCode.JWT_EXPIRED,
          "Token has expired"
      );
    } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
      throw new SecurityException(
          ExceptionCode.TOKEN_FORMAT_ERROR,
          "Invalid authentication token"
      );
    }
  }

  private Key getSigningKey(String secret) {
    if (secret.length() < 32) {
      throw new SecurityException(
          ExceptionCode.JWT_INTERNAL_ERROR,
          "JWT secret too short: " + secret.length()
      );
    }

    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }
}
