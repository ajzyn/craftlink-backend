package com.craftlink.backend.auth.application.service;

import com.craftlink.backend.auth.adapter.persistence.write.RefreshTokenRepositoryAdapter;
import com.craftlink.backend.auth.domain.model.refreshToken.RefreshToken;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.ExpirationDate;
import com.craftlink.backend.auth.domain.model.user.vo.UserId;
import com.craftlink.backend.auth.domain.port.security.SecureTokenGenerator;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenIssuer {

  private final RefreshTokenRepositoryAdapter repository;
  private final SecureTokenGenerator tokenGen;

  @Value("${security.refresh.expiresSeconds:604800}") // 7 dni domyślnie
  private long ttl;

  @Transactional
  public RefreshToken issueForUser(UUID userId) {
    var tokenValue = tokenGen.generateBase64Url(64);
    var token = RefreshToken.create(
        new UserId(userId),
        tokenValue,
        new ExpirationDate(Instant.now().plus(ttl, ChronoUnit.SECONDS))
    );
    
    return repository.save(token);
  }
}
