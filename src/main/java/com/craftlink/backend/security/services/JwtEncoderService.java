package com.craftlink.backend.security.services;

import com.craftlink.backend.config.exceptions.ExceptionCode;
import com.craftlink.backend.config.exceptions.custom.InvalidJwtException;
import com.craftlink.backend.config.exceptions.custom.JwtGenerationException;
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


    public String generateToken(String subject, Long expTime, String secret){
        if (expTime == null) {
            throw new JwtGenerationException(ExceptionCode.JWT_EXP_TIME_NULL);
        }

        try{
            Date tokenExpTime = Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + expTime));

            return Jwts.builder()
                .setExpiration(tokenExpTime)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(getSigningKey(secret))
                .compact();
        }catch (JwtException e) {
            throw new JwtGenerationException(ExceptionCode.JWT_GENERAL_EXCEPTION);
        }
    }

    public String generateToken(String subject, Map<String, String> claims, Long expTime, String secret){
        if (expTime == null) {
            throw new JwtGenerationException(ExceptionCode.JWT_EXP_TIME_NULL);
        }

        try{
            Date tokenExpTime = Date.from(Instant.ofEpochMilli(System.currentTimeMillis() + expTime));

            return Jwts.builder()
                .setExpiration(tokenExpTime)
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .signWith(getSigningKey(secret))
                .compact();
        }catch (JwtException e) {
            throw new JwtGenerationException(ExceptionCode.JWT_GENERAL_EXCEPTION);
        }
    }

    public String extractSubject(String token, String secret){
        try{

            Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();

            return claims.getSubject();
        }catch (ExpiredJwtException e) {
            throw new InvalidJwtException(ExceptionCode.JWT_EXPIRED, e);

        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new InvalidJwtException(ExceptionCode.JWT_NOT_VALID, e);
        }
    }

    private Key getSigningKey(String secret){
        if(secret.length() < 32){
            throw new JwtGenerationException(ExceptionCode.JWT_SECRET_TOO_SHORT);
        }

        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
