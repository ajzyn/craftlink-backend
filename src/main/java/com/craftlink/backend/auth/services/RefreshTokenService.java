package com.craftlink.backend.auth.services;

import com.craftlink.backend.auth.entities.RefreshTokenEntity;
import com.craftlink.backend.auth.models.TokenType;
import com.craftlink.backend.auth.properties.RefreshTokenCookieProperties;
import com.craftlink.backend.auth.repositories.RefreshTokenRepository;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.custom.ValidationException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import com.craftlink.backend.security.services.AccessTokenService;
import com.craftlink.backend.shared.cookies.CookieService;
import com.craftlink.backend.user.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;
    private final AccessTokenService accessTokenService;
    private final UserService userService;
    private final RefreshTokenCookieProperties refreshTokenCookieProperties;

    @Transactional
    public RefreshTokenEntity createRefreshToken(String userEmail) {
        var user = userService.getUserByEmail(userEmail);
        var token = generateRefreshToken();
        var expirationDate = Instant.now()
            .plus(refreshTokenCookieProperties.getExpirationTimeInSeconds(), ChronoUnit.SECONDS);

        var refreshTokenEntity = RefreshTokenEntity.builder()
            .user(user)
            .token(token)
            .expiryDate(expirationDate)
            .build();

        return refreshTokenRepository.save(refreshTokenEntity);
    }

    public String refreshAccessToken(HttpServletRequest request) {
        var rawToken = cookieService.getCookie(request, TokenType.REFRESH.getName());
        if (rawToken == null || rawToken.isBlank()) {
            throw new ValidationException(
                ExceptionCode.MISSING_REQUIRED_FIELD,
                Map.of("refreshToken", "No required token")
            );
        }

        var refreshTokenEntity = refreshTokenRepository
            .findByToken(rawToken)
            .orElseThrow(() -> new SecurityException(ExceptionCode.REFRESH_TOKEN_INVALID));

        if (Instant.now().isAfter(refreshTokenEntity.getExpiryDate())) {
            refreshTokenRepository.delete(refreshTokenEntity);
            throw new SecurityException(ExceptionCode.REFRESH_TOKEN_EXPIRED);
        }

        return accessTokenService.generateAccessToken(refreshTokenEntity.getUser().getEmail());
    }

    private String generateRefreshToken() {
        var randomBytes = new byte[64];
        var secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
