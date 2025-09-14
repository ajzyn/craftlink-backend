package com.craftlink.backend.auth.application.service;

import com.craftlink.backend.auth.application.dto.AuthResult;
import com.craftlink.backend.auth.application.port.repository.UserQueryRepository;
import com.craftlink.backend.auth.application.port.usecase.RefreshTokenUseCase;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.auth.domain.port.RefreshTokenRepository;
import com.craftlink.backend.auth.domain.port.security.AccessTokenGenerator;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

  private final RefreshTokenRepository repository;
  private final AccessTokenGenerator accessTokenGenerator;
  private final UserQueryRepository userQueryRepository;

  @Transactional
  public AuthResult handle(String rawRefreshToken) {
    if (StringUtils.isBlank(rawRefreshToken)) {
      throw new SecurityException(ExceptionCode.REFRESH_TOKEN_INVALID);
    }

    var rt = repository.findByToken(new RefreshTokenValue(rawRefreshToken))
        .orElseThrow(() -> new SecurityException(ExceptionCode.REFRESH_TOKEN_INVALID));

    if (Instant.now().isAfter(rt.getExpirationDate().value())) {
      repository.delete(rt);
      throw new SecurityException(ExceptionCode.REFRESH_TOKEN_EXPIRED);
    }

    var userSnapshot = userQueryRepository.findById(rt.getUserId())
        .orElseThrow(() -> new SecurityException(ExceptionCode.USER_NOT_FOUND));

    return new AuthResult(accessTokenGenerator.generateAccessToken(userSnapshot));
  }
}
