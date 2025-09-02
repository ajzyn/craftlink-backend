package com.craftlink.backend.config.security.services;

import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.custom.ValidationException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
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
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JwtEncoderService {

  public String generateToken(String subject, Map<String, String> claims, Long expTime, String secret) {
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

  public String extractSubject(String token, String secret) {
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
