package com.craftlink.backend.security.services;

import com.craftlink.backend.config.exceptions.ExceptionCode;
import com.craftlink.backend.config.exceptions.custom.InvalidJwtException;
import com.craftlink.backend.config.exceptions.custom.JwtGenerationException;
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
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final JwtProperties jwtProperties;
    private final JwtEncoderService jwtEncoderService;

    public String getUserEmail(String token){
        return jwtEncoderService.extractSubject(token, jwtProperties.getSecret());
    }

    public String generateAccessToken(String email){
       return jwtEncoderService.generateToken(email, jwtProperties.getExpirationTime(), jwtProperties.getSecret());
    }
}
