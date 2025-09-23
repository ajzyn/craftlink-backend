package com.craftlink.backend.auth.application.command;

import com.craftlink.backend.auth.application.port.in.command.refreshToken.RefreshTokenCommand;
import com.craftlink.backend.auth.application.port.in.command.refreshToken.RefreshTokenUseCase;
import com.craftlink.backend.auth.application.port.in.command.shared.AuthResult;
import com.craftlink.backend.auth.application.port.out.read.UserQueryRepository;
import com.craftlink.backend.auth.application.port.out.security.AccessTokenGenerator;
import com.craftlink.backend.auth.application.port.out.write.RefreshTokenRepository;
import com.craftlink.backend.auth.domain.model.refreshToken.vo.RefreshTokenValue;
import com.craftlink.backend.infrastructure.exceptions.custom.SecurityException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenHandler implements RefreshTokenUseCase {

  private final RefreshTokenRepository repository;
  private final AccessTokenGenerator accessTokenGenerator;
  private final UserQueryRepository userQueryRepository;

  @Transactional
  public AuthResult handle(RefreshTokenCommand cmd) {
    if (StringUtils.isBlank(cmd.rawToken())) {
      throw new SecurityException(ExceptionCode.REFRESH_TOKEN_INVALID);
    }

    var rt = repository.findByToken(new RefreshTokenValue(cmd.rawToken()))
        .orElseThrow(() -> new SecurityException(ExceptionCode.REFRESH_TOKEN_INVALID));

    if (rt.isActive(Instant.now())) {
      repository.delete(rt);
      throw new SecurityException(ExceptionCode.REFRESH_TOKEN_EXPIRED);
    }

    var userSnapshot = userQueryRepository.findById(rt.getUserId())
        .orElseThrow(() -> new SecurityException(ExceptionCode.USER_NOT_FOUND));

    return new AuthResult(accessTokenGenerator.generateAccessToken(userSnapshot));
  }
}
