package com.craftlink.backend.auth.application.usecase;

import com.craftlink.backend.auth.application.port.UserReadRepository;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.auth.domain.port.security.AccessTokenGenerator;
import com.craftlink.backend.auth.domain.port.token.RefreshTokenRepository;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

  private final RefreshTokenRepository repository;
  private final AccessTokenGenerator accessTokenGenerator;
  private final UserReadRepository userReadRepository;

  @Override
  @Transactional
  public String refresh(String rawRefreshToken) {
    var rt = repository.findByToken(new RefreshTokenValue(rawRefreshToken))
        .orElseThrow(() -> new SecurityException(ExceptionCode.REFRESH_TOKEN_INVALID));

    if (Instant.now().isAfter(rt.getExpirationDate().value())) {
      repository.delete(rt);
      throw new SecurityException(ExceptionCode.REFRESH_TOKEN_EXPIRED);
    }

    var userSnapshot = userReadRepository.findById(rt.getUserId())
        .orElseThrow(() -> new SecurityException(ExceptionCode.USER_NOT_FOUND));

    return accessTokenGenerator.generateAccessToken(userSnapshot);
  }
}
